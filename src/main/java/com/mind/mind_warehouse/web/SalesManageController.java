package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.SalesManagement;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.SaleManageService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.SaleTradeVo;
import com.mind.mind_warehouse.vo.SalesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("saleManage")
@Api(value = "销售管理接口")
public class SalesManageController {
    //订单状态集合
    private static List<Integer> statusList = Arrays.asList(
           0,1,2,3
    );

    @Autowired
    SaleManageService sms;

    //查询
    @GetMapping("/list")
    @ApiOperation(value = "查询销售订单列表")
    public ResultResponse<PageInfo<SalesVo>> queryList(@RequestParam(value = "pageNum", defaultValue = "1") Integer now,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer size,
                                          String pcode,
                                          String pname,
                                          String orderNum,
                                          Integer status) {
        ResultResponse<PageInfo<SalesVo>> result;
        try {
            PageInfo<SalesVo> pageInfo = sms.queryOnPageByConditon(now, size, orderNum, pcode, pname, status);
            System.out.println(pageInfo);
            result = new ResultResponse<>(200, "查询销售列表成功!", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询销售列表失败!");
        }
        return result;
    }

    @GetMapping("/returnList")
    @ApiOperation(value = "查询退货订单列表")
    public ResultResponse<PageInfo<SalesVo>> queryReturnList(@RequestParam(value = "pageNum", defaultValue = "1") Integer now,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer size,
                                                       String pcode,
                                                       String pname,
                                                       String returnNum) {
        ResultResponse<PageInfo<SalesVo>> result;
        try {
            PageInfo<SalesVo> pageInfo = sms.queryReturnOnPageByConditon(now, size, returnNum, pcode, pname);
            System.out.println(pageInfo);
            result = new ResultResponse<>(200, "查询销售列表成功!", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询销售列表失败!");
        }
        return result;
    }

    //新增
    @PostMapping("/add")
    @ApiOperation(value = "新增销售订单")
    public ResultResponse<Void> add(@RequestBody SalesManagement sale) {
        ResultResponse<Void> result;

        try {
            sale.setOrderNumber(CodeCreateUtil.getCode(CodeDirectory.SALE_CODE));
            sale.setCreateTime(new Date());
            sale.setUpdateTime(new Date());
            sale.setIsDelete(0);

            int res = sms.addData(sale);

            if (res > 0) {
                result = new ResultResponse<>(200, "添加销售订单成功!");
            } else {
                result = new ResultResponse<>(201, "销售订单数据缺失!");
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加销售订单失败!");
        }

        return result;
    }

    //编辑
    @PutMapping("/modify")
    @ApiOperation(value = "编辑销售订单")
    public ResultResponse<Void> modify(@RequestBody SalesManagement sale) {
        ResultResponse<Void> result;

        try {

            if (sale.getId() == null) {
                return new ResultResponse<>(201, "销售订单数据缺失!");
            }

            SalesManagement salesInfo = sms.getSalesInfoById(sale.getId());

            //不专门一个变量一个变量赋值了

            //一些不能乱改的数据用查询的值
            sale.setCreateTime(salesInfo.getCreateTime());
            sale.setIsDelete(salesInfo.getIsDelete());
            sale.setOrderNumber(salesInfo.getOrderNumber());
            sale.setUpdateTime(new Date());

            sms.updateData(sale, salesInfo.getQuantity());

            result = new ResultResponse<>(200, "更新销售订单成功!");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "更新销售订单失败!");
        }

        return result;
    }

    //删除
    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除销售订单")
    public ResultResponse<Void> remove(@PathVariable("id") Integer id) {
        ResultResponse<Void> result;
        try {

            SalesManagement salesInfo = sms.getSalesInfoById(id);

            if (salesInfo == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }


            salesInfo.setIsDelete(1);

            salesInfo.setUpdateTime(new Date());

            sms.updateSale(salesInfo);

            result = new ResultResponse<>(200, "删除成功!");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除失败!");
        }

        return result;
    }

    //查看订单详情
    public ResultResponse<Void> queryInfo() {
        ResultResponse<Void> result;

        return null;
    }

    //订单状态变更 0:订单待出货,1:出货,2:退货待确认,3:确认退货
    @PostMapping("/status")
    @ApiOperation(value = "订单状态变更")
    public ResultResponse<Void> salesReturn(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") Integer status) {
        ResultResponse<Void> result;
        try {
            System.out.println(id + "---" + status);
            SalesManagement salesInfo = sms.getSalesInfoById(id);

            if (salesInfo == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }

            if (!statusList.contains(status)) {
                return new ResultResponse<>(201, "订单状态不合法!");
            }
            //退货要生成退货单号
            if (status == 2) {
                String reCode = CodeCreateUtil.getCode(CodeDirectory.SALE_RETURN_CODE);
                salesInfo.setReturnOrder(reCode);
            }

            salesInfo.setUpdateTime(new Date());
            salesInfo.setStatus(status);

            sms.updateSale(salesInfo);

            result = new ResultResponse<>(200, "变更订单状态成功!");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "变更订单状态失败!");
        }

        return result;
    }


    @PutMapping("/enter")
    @ApiOperation(value = "入账")
    public ResultResponse<Void> enterPay(@RequestParam(value = "id") Integer id, @RequestParam(value = "tradeNo") String tradeNo) {
        ResultResponse<Void> result;
        try {
            if (id == null || id == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }

            SalesManagement salesInfo = sms.getSalesInfoById(id);

            if (salesInfo == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }

            int res = sms.addTradeNo(id, tradeNo);
            if (res == -1) {
                return new ResultResponse<>(201, "订单流水号不存在!");
            }

            result = new ResultResponse<>(200, "入账成功!");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "入账失败!");
        }

        return result;
    }

    @PutMapping("/updateReturnIn")
    @ApiOperation(value = "更新退货入库单号")
    public ResultResponse<Void> updateReturnInNumber(@RequestParam(value = "id") Integer id, @RequestParam(value = "returnInNumber") String returnNo) {
        ResultResponse<Void> result;
        try {
            if (id == null || id == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }

            if (returnNo == null || returnNo == "") {
                return new ResultResponse<>(200, "更新成功!");
            }

            SalesManagement salesInfo = sms.getSalesInfoById(id);

            if (salesInfo == null) {
                return new ResultResponse<>(201, "没有查询到订单信息!");
            }

            int res = sms.addReturnIn(id, returnNo);
            if (res == -1) {
                return new ResultResponse<>(201, "订单退货入库单号不存在!");
            }

            result = new ResultResponse<>(200, "更新成功!");

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "更新失败!");
        }

        return result;
    }

//    @GetMapping("/download")
//    public void downloadExcel(@RequestParam(value = "ids") int[] ids, HttpServletResponse response) throws IOException {
//
//        if (ids.length < 1) {
//            return;
//        }
//
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("utf-8");
//        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String name = "销售报表" + (new Date()).toString();
//        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
//        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//
//        //查询数据
//        List<SalesVo> list = sms.queryByIds(ids);
//        //OutputStream outputStream = response.getOutputStream();
//
//        try {
//            EasyExcel.write(response.getOutputStream(), Warehouse.class).sheet("模板").doWrite(list);
//            //outputStream.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            //outputStream.close();
//        }
//    }

}
