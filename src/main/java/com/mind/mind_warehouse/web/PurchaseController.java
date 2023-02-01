package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.entity.WarehousePurchaseReturn;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehousePurchaseReturnService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.PurchaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Purchase")
public class PurchaseController {
    @Autowired
    WarehousePurchaseReturnService warehousePurchaseReturnService;
    @GetMapping("/queryPurchaseByCons")
    public ResultResponse<PageInfo<PurchaseVo>> queryPurchaseByCons(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                    @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                    Integer num,String code,String name,Integer status){
        ResultResponse<PageInfo<PurchaseVo>> result=null;
        try {
            PageInfo<PurchaseVo> purchaseVoPageInfo = warehousePurchaseReturnService.queryPurchaseVoByCons(pageNum, pageSize, num, code, name, status);
            result=new ResultResponse<>(200,"查询采购列表成功",purchaseVoPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "查询采购列表失败");
        }
        return result;
    }
    @GetMapping("/queryPurchaseWithReturnByCons")
    public ResultResponse<PageInfo<PurchaseVo>> queryPurchaseWithReturnByCons(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                    @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                    Integer returnNum,String code,String name,Integer status){
        ResultResponse<PageInfo<PurchaseVo>> result=null;
        try {
            PageInfo<PurchaseVo> purchaseVoPageInfo = warehousePurchaseReturnService.queryPurchaseVoByWithReturnCons(pageNum, pageSize, returnNum, code, name, status);
            result=new ResultResponse<>(200,"查询采购列表成功",purchaseVoPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "查询采购列表失败");
        }
        return result;
    }





    @DeleteMapping("/deletePurchase")
    public ResultResponse<Void> deletePurchase(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
            warehousePurchaseReturnService.removeById(id);
            result=new ResultResponse<>(200,"删除采购信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "删除采购信息失败");
        }
        return result;
    }
    @PostMapping("/addPurchase")
    public ResultResponse<Void> addPurchase(@RequestBody WarehousePurchaseReturn record){
        ResultResponse<Void> result=null;
        try {
        String code = CodeCreateUtil.getCode(CodeDirectory.PURCHASE_CODE);
            String substring = code.substring(5);
            int i = Integer.parseInt(substring);
            record.setNum(i);
            warehousePurchaseReturnService.add(record);
            result=new ResultResponse<>(200,"增加采购信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "增加采购信息失败");
        }
        return result;
    }
    @PutMapping("/modifyPurchase")
    public ResultResponse<Void> modifyPurchase(@RequestBody WarehousePurchaseReturn record){
        ResultResponse<Void> result=null;
        try {
            warehousePurchaseReturnService.modify(record);
            result=new ResultResponse<>(200,"增加采购信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "增加采购信息失败");
        }
        return result;
    }
    @GetMapping("/modifyReturnNum")
    public ResultResponse<Void> modifyReturnNum(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
        WarehousePurchaseReturn warehousePurchaseReturn = warehousePurchaseReturnService.queryByPrimaryKey(id);
        String code = CodeCreateUtil.getCode(CodeDirectory.PURCHASE_RETURN_CODE);
            String substring = code.substring(5);
            warehousePurchaseReturn.setReturnNum(substring);
        warehousePurchaseReturn.setStatus(3);
        warehousePurchaseReturnService.modify(warehousePurchaseReturn);
            result=new ResultResponse<>(200,"退货成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "退货失败");
        }
        return result;
    }
    @GetMapping("/queryPurchaseById")
    public ResultResponse<WarehousePurchaseReturn> queryPurchaseById(@RequestParam("id")Integer id){
        ResultResponse<WarehousePurchaseReturn> result=null;
        try {
            WarehousePurchaseReturn warehousePurchaseReturn = warehousePurchaseReturnService.queryByPrimaryKey(id);
            result=new ResultResponse<>(200,"查询采购信息成功",warehousePurchaseReturn);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "查询采购信息失败");
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
        String name = "采购报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
//        List<Warehouse> list = wms.queryByIds(ids);
        List<PurchaseVo> purchaseVos = warehousePurchaseReturnService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), PurchaseVo.class).sheet("采购报表").doWrite(purchaseVos);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }

}
