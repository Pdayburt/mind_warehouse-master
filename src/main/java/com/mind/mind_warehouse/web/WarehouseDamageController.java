package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehouseDamageService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.WarehouseDamageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("damage")
@Api(value = "报损管理接口")
public class WarehouseDamageController {

    @Autowired
    WarehouseDamageService warehouseDamageService;

    //报损记录分页查询
    @GetMapping("/queryWarehouseDamageByPage")
    @ApiOperation(value = "报损管理相关查询,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now", value = "查询当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页单位,表示当前页大小", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "wdtype", value = "根据报损类型查找", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "wdcode", value = "根据报损单号查找", dataType = "String", paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseDamageVo>> queryWarehouseDamageByPage(@RequestParam(value = "now", defaultValue = "1") int now,
                                                                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                                                                  @RequestParam(value = "wdtype", defaultValue = "0") Integer wdtype, String wdcode) {
        ResultResponse<PageInfo<WarehouseDamageVo>> result = null;

        try {
            PageInfo<WarehouseDamageVo> pageInfo = warehouseDamageService.queryWarehouseDamageByPage(now, size, wdtype, wdcode);
            result = new ResultResponse<>(200, "查询报损信息成功", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询报损信息失败,等下再来试试");
        }
        return result;
    }


    //商品信息查询
    @GetMapping("/queryAllProduct")
    @ApiOperation(value = "商品信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now", value = "查询当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页单位,表示当前页大小", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pcode", value = "根据产品编码查找", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pname", value = "根据产品名查找", dataType = "String", paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseDamageVo>> queryAllProduct(@RequestParam(value = "now", defaultValue = "1") int now,
                                                                       @RequestParam(value = "size", defaultValue = "3") int size,
                                                                       String pcode, String pname) {
        ResultResponse<PageInfo<WarehouseDamageVo>> result = null;

        try {
            PageInfo<WarehouseDamageVo> pageInfo = warehouseDamageService.queryAllProduct(now, size, pcode, pname);
            result = new ResultResponse<>(200, "查询产品信息成功", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询产品信息失败,等下再来试试");
        }
        return result;
    }


    //商品信息查询
    @GetMapping("/queryAllProductPlus")
    @ApiOperation(value = "商品信息查询Plus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now", value = "查询当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页单位,表示当前页大小", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pcode", value = "根据产品编码查找", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pname", value = "根据产品名查找", dataType = "String", paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseDamageVo>> queryAllProductPlus(@RequestParam(value = "now", defaultValue = "1") int now,
                                                                           @RequestParam(value = "size", defaultValue = "3") int size,
                                                                           String pcode, String pname) {
        ResultResponse<PageInfo<WarehouseDamageVo>> result = null;

        try {
            PageInfo<WarehouseDamageVo> pageInfo = warehouseDamageService.queryAllProductPlus(now, size, pcode, pname);
            result = new ResultResponse<>(200, "查询产品信息成功", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询产品信息失败,等下再来试试");
        }
        return result;
    }


    //商品信息查询
    @GetMapping("/queryProduct")
    @ApiOperation(value = "商品信息查询,不与报损表关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now", value = "查询当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页单位,表示当前页大小", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pcode", value = "根据产品编码查找", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pname", value = "根据产品名查找", dataType = "String", paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseDamageVo>> queryProduct(@RequestParam(value = "now", defaultValue = "1") int now,
                                                                    @RequestParam(value = "size", defaultValue = "3") int size,
                                                                    String pcode, String pname) {
        ResultResponse<PageInfo<WarehouseDamageVo>> result = null;

        try {
            PageInfo<WarehouseDamageVo> pageInfo = warehouseDamageService.queryProduct(now, size, pcode, pname);
            result = new ResultResponse<>(200, "查询产品信息成功", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询产品信息失败,等下再来试试");
        }
        return result;
    }


    //新增报损
    @PostMapping("/add")
    @ApiOperation(value = "报损信息的添加操作/封装对象传递")
    public ResultResponse<Void> add(@RequestBody WarehouseDamageVo warehouseDamageVo) {
        ResultResponse<Void> result = null;
        try {
            String wdCode = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_DAMAGE);
            String batch = CodeCreateUtil.getBatch();
            warehouseDamageVo.setWdcode(wdCode);
            warehouseDamageVo.setBatch(batch);
            warehouseDamageVo.setCreateTime(new Date());
            warehouseDamageVo.setUpdateTime(new Date());
            warehouseDamageService.addWarehouseDamage(warehouseDamageVo);
            result = new ResultResponse<>(200, "添加报损信息成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加报损信息失败,请稍后再试");
        }
        return result;
    }


    //不做报损修改

    //删除报损记录

    @DeleteMapping("/del{wdid}")
    @ApiOperation(value = "根据单个id删除报损信息的操作")
    public ResultResponse<Void> del(@PathVariable("wdid") int wdid) {


        ResultResponse<Void> result = null;
        try {
            warehouseDamageService.removeWarehouseDamageByKey(wdid);
            result = new ResultResponse<>(200, "删除报损信息成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除报损信息失败");
        }
        return result;
    }

    //根据报损表id查询报损表信息
    @GetMapping("/queryById/{wdid}")
    @ApiOperation(value = "根据id查询报损信息的操作")
    public ResultResponse<WarehouseDamageVo> queryById(@PathVariable("wdid") int wdid) {

        ResultResponse<WarehouseDamageVo> result = null;
        try {
            WarehouseDamageVo warehouseDamage = warehouseDamageService.queryWarehouseDamageById(wdid);
            result = new ResultResponse<>(200, "查询报损信息成功", warehouseDamage);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询报损信息失败");
        }
        return result;
    }


    //更新报损
    @PutMapping("/modify")
    @ApiOperation(value = "报损信息的修改操作/封装对象传递")
    public ResultResponse<Void> modify(@RequestBody WarehouseDamageVo warehouseDamageVo) {
        ResultResponse<Void> result = null;
        try {

            WarehouseDamageVo damage = warehouseDamageService.queryWarehouseDamageById(warehouseDamageVo.getWdid());
            damage.setWdtype(warehouseDamageVo.getWdtype());
            damage.setDamageNum(warehouseDamageVo.getDamageNum());
            damage.setWdcode(warehouseDamageVo.getWdcode());
            damage.setCreateTime(warehouseDamageVo.getCreateTime());
            warehouseDamageService.modifyWarehouseDamage(warehouseDamageVo);
            result = new ResultResponse<>(200, "更新报损信息成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "更新报损信息失败,请稍后再试!");
        }
        return result;

    }



    @GetMapping("/download")
    public void downloadExcel(@RequestParam(value = "ids") int[] ids, HttpServletResponse response) throws IOException {

        if (ids.length < 1) {
            return;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String name = "产品报损" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<WarehouseDamageVo> list = warehouseDamageService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), WarehouseDamageVo.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }






}