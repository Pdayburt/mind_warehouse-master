package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.mind.mind_warehouse.entity.ProductType;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.ProductTypeService;
import com.mind.mind_warehouse.vo.PurchaseVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
//=======


//>>>>>>> origin/master:src/main/java/com/mind/mind_warehouse/web/productTypeController.java

@RestController
@Api(value = "产品类别相关的操作")
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;
//<<<<<<< HEAD:src/main/java/com/mind/mind_warehouse/web/ProductTypeController.java

    @ApiOperation(value = "分页查询产品类别")

    @GetMapping("/queryProductType")
    @ApiImplicitParams(
            {
            @ApiImplicitParam(name = "pageNum", value = "查询的页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页码的大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "产品的名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id",value = "产品的id",dataType = "String",paramType = "query")
    }
    )
    public ResultResponse<PageInfo<ProductType>> queryProductType(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                            @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                            String name,
                                                            Integer code){
        ResultResponse<PageInfo<ProductType>> result = null;
        try {
            PageInfo<ProductType> productTypePageInfo = productTypeService.queryProductByTypeIdAndName(pageNum, pageSize, code, name);
            result=new ResultResponse<>(200,"查询产品类别成功",productTypePageInfo);
            Gson gson = new Gson();
            String s = gson.toJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询产品类别失败");
        }
        return result;
    }
    @PostMapping("/addProductType")

    @ApiOperation(value = "增加产品类型的操作")
    public ResultResponse<ProductType> addProductType(@ApiParam @RequestBody ProductType productType){
        ResultResponse<ProductType> result = null;
        try {
            productTypeService.add(productType);
            result=new ResultResponse<>(200, "增加产品类别成功", productType);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(200, "增加产品类别成功");
        }
        return result;
    }
    @DeleteMapping("/delProductTypeById")
    @ApiOperation(value = "删除产品类型的操作")

    public ResultResponse<Void> delProductTypeById(@RequestParam(value = "PTid")Integer id){
        ResultResponse<Void> result = null;
        try {
            productTypeService.remove(id);
            result=new ResultResponse<>(200, "删除产品类别成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "删除产品类别失败");
        }
        return result;
    }
    @PutMapping("/modifyProductType")
    @ApiOperation(value = "修改产品类型的操作")

    public ResultResponse<ProductType> modifyProductType(@ApiParam @RequestBody ProductType productType){
        ResultResponse<ProductType> result=null;
        try {
            productTypeService.modify(productType);
            result=new ResultResponse<>(200, "修改产品类型成功",productType);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "修改产品类型失败");
        }
        return result;
    }
    @GetMapping("/queryAllFatherProductType")
    public ResultResponse<List<ProductType>> queryAllFatherProductType(){
        ResultResponse<List<ProductType>> result=null;
        try {
            List<ProductType> productTypes = productTypeService.queryAllFatherProductType();
            result=new ResultResponse<>(200,"查询所有父菜单成功",productTypes);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询所有父菜单成功");
        }
        return result;
    }
    @GetMapping("/queryNotFatherProductType")
    public ResultResponse<List<ProductType>> queryNotFatherProductType(){
        ResultResponse<List<ProductType>> result=null;
        try {
            List<ProductType> productTypes = productTypeService.queryNotFatherProductType();
            result=new ResultResponse<>(200,"查询所有子菜单成功",productTypes);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询所有子菜单成功");
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
        String name = "产品类型报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
//        List<Warehouse> list = wms.queryByIds(ids);
        List<ProductType> productTypes = productTypeService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), ProductType.class).sheet("产品类型报表").doWrite(productTypes);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }


}
