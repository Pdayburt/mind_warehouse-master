package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.ProductService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.ProductVo;
import com.mind.mind_warehouse.vo.PurchaseVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Product")
@Api(value = "产品相关操作")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/addProduct")
    @ApiOperation("添加产品")

    public ResultResponse<Product> addProduct(@RequestBody Product product){
        ResultResponse<Product> result=null;
        String code = CodeCreateUtil.getCode(CodeDirectory.PRODUCT_CODE);
        String substring = code.substring(5);
        product.setCode(substring);
        try {
            productService.add(product);
            result= new ResultResponse<>(200, "添加产品成功",product);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加产品失败");
        }
        return result;
    }
    @DeleteMapping("/deleteProduct")
    @ApiOperation(value = "删除产品")

    @ApiImplicitParam(name = "id",value = "要删除的产品id",dataType = "int",paramType = "query")
    public ResultResponse<Void> deleteProduct(Integer id){
        ResultResponse<Void> result=null;
        try {
            productService.remove(id);
            result= new ResultResponse<>(200, "删除产品成功");
        } catch (Exception e) {
            e.printStackTrace();
            result= new ResultResponse<>(201, "删除产品失败");
        }
        return result;
    }
    @PutMapping("/updateProduct")
    @ApiOperation(value = "更新产品")

    public ResultResponse<Product> updateProduct(@ApiParam @RequestBody Product product){
        ResultResponse<Product> result=null;
        try {
            productService.modify(product);
            result=new ResultResponse<>(200,"更新产品成功",product);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "更新产品失败");
        }
        return result;
    }

    @GetMapping("/queryProductByCons")
    @ApiOperation(value = "查询产品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页码",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "code",value = "编号",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "产品名",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type",value = "类别编号",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "tel",value = "电话",dataType = "String",paramType = "query"),
    })
    public ResultResponse<PageInfo<ProductVo>> queryProductByCons(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                Integer code, String name,Integer type,String tel){
        ResultResponse<PageInfo<ProductVo>> result=null;
        try {
            PageInfo<ProductVo> productPageInfo = productService.queryProductByCons(pageNum, pageSize, code, name, type, tel);
            result=new ResultResponse<>(200,"分页查询产品成功",productPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "分页查询产品失败");
        }
        return result;
    }
    @GetMapping("/queryProductById")
    public ResultResponse<Product> queryProductById(@RequestParam("id")Integer id){
        ResultResponse<Product> result=null;
        try {
            Product product = productService.queryProductById(id);
            result=new ResultResponse<>(200,"查询产品成功",product);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(200,"查询产品失败");
        }
        return result;
    }
    @GetMapping("/queryAllProduct")
    public ResultResponse<List<Product>> queryAllProduct(){
        ResultResponse<List<Product>> result=null;
        try {
            List<Product> products = productService.queryAll();
            result=new ResultResponse<>(200,"查询所有产品成功",products);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询所有产品失败");
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
        String name = "产品报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
//        List<Warehouse> list = wms.queryByIds(ids);
//        List<PurchaseVo> purchaseVos = warehousePurchaseReturnService.queryByIds(ids);
        List<ProductVo> productVos = productService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), ProductVo.class).sheet("产品报表").doWrite(productVos);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }

}
