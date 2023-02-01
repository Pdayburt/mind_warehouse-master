package com.mind.mind_warehouse.web;


import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehouseMoveService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.SalableStockVo;
import com.mind.mind_warehouse.vo.ShowWarehouseAndStorageVo;
import com.mind.mind_warehouse.vo.WarehouseDamageVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
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
@RequestMapping("move")
@Api(value = "对产品移库的相关操作")
public class WarehouseMoveController {

    @Autowired
    WarehouseMoveService warehouseMoveService;



    @GetMapping("/initWarehouseMove")
    @ApiOperation(value = "移库记录相关查询,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "moveNum",value = "根据移库单号模糊查找",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "moveType",value = "根据移库类型查找",dataType = "int",paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseMoveVo>> initUnits(@RequestParam(value = "now",defaultValue = "1") int now,
                                                               @RequestParam(value = "size",defaultValue = "3") int size, String moveNum,
                                                               @RequestParam(value = "moveType",defaultValue = "0") Integer moveType){
        ResultResponse<PageInfo<WarehouseMoveVo>> result = null;

        try {
            PageInfo<WarehouseMoveVo> pageInfo = warehouseMoveService.findWarehouseMoveBuyPage(now, size, moveNum,moveType);
            result = new ResultResponse<>(200,"查询移库信息成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询移库信息失败,等下再来试试");
        }
        return result;
    }


    @GetMapping("/queryAllProduct")
    @ApiOperation(value = "查询所有产品信息,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pname",value = "根据产品名模糊查找",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pcode",value = "根据产品编码查找",dataType = "String",paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseMoveVo>> queryAllProduct(@RequestParam(value = "now",defaultValue = "1") int now,
                                                               @RequestParam(value = "size",defaultValue = "3") int size, String pname, String pcode){
        ResultResponse<PageInfo<WarehouseMoveVo>> result = null;

        try {
            PageInfo<WarehouseMoveVo> pageInfo = warehouseMoveService.findAllProduct(now, size, pname,pcode);
            result = new ResultResponse<>(200,"查询商品信息成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询商品信息失败,等下再来试试");
        }
        return result;
    }










    @GetMapping("/findById/{wwmid}")
    @ApiOperation(value = "根据id查询移库表信息")
    public ResultResponse<WarehouseMoveVo> findById(@PathVariable("wwmid") int wwmid){
        System.out.println(wwmid+"+++==============================================");
        ResultResponse<WarehouseMoveVo> result = null;
        try {
            WarehouseMoveVo warehouseMove = warehouseMoveService.findWarehouseMoveById(wwmid);
            result = new ResultResponse<>(200,"查询移库信息成功",warehouseMove);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询移库信息失败,等下再来试试");
        }
        return result;
    }


    @DeleteMapping("/del{id}")
    @ApiOperation(value = "根据单个id删除移库记录的操作")
    public ResultResponse<Void> del(@PathVariable("id") int id){


        ResultResponse<Void> result = null;
        try {
            warehouseMoveService.removeWarehouseMoveById(id);
            result = new ResultResponse<>(200,"删除移库信息成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除移库信息失败");
        }
        return result;
    }


    @PostMapping("/add")
    @ApiOperation(value = "移库信息的添加操作/封装对象传递")
    public ResultResponse<Void> add(@RequestBody WarehouseMoveVo warehouseMoveVo){
        ResultResponse<Void> result = null;
        try {
            String batch = CodeCreateUtil.getBatch();
            String WmCode = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_MOVE_CODE);
            warehouseMoveVo.setMoveNum(WmCode);
            warehouseMoveVo.setBatch(batch);
            warehouseMoveVo.setCreateTime(new Date());
            warehouseMoveVo.setUpdateTime(new Date());
            warehouseMoveService.add(warehouseMoveVo);
            result = new ResultResponse<>(200,"添加移库信息成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加移库信息失败,请稍后再试");
        }
        return result;
    }




    @PutMapping("/modify")
    @ApiOperation(value = "移库信息的修改操作/封装对象传递")
    public ResultResponse<Void> modify(@RequestBody WarehouseMoveVo warehouseMoveVo){
        ResultResponse<Void> result = null;
        try {
            WarehouseMoveVo w = warehouseMoveService.findWarehouseMoveById(warehouseMoveVo.getWmid());
            w.setNewStorageId(warehouseMoveVo.getNewStorageId());
            w.setMoveType(warehouseMoveVo.getMoveType());
            w.setWmnote(warehouseMoveVo.getWmnote());
            w.setUpdateTime(new Date());
            warehouseMoveService.modifyWarehouseMove(warehouseMoveVo);
            result = new ResultResponse<>(200,"更新移库信息成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"更新移库信息失败");
        }
        return result;
    }





    @GetMapping("/showWarehouseAndStorage")
    @ApiOperation(value = "查询仓库和库位的相关操作")
    public ResultResponse<ShowWarehouseAndStorageVo> showMenus(int id){
        ResultResponse<ShowWarehouseAndStorageVo> result = null;
        try {
            ShowWarehouseAndStorageVo warehouseAndStorage = warehouseMoveService.findWarehouseAndStorageById(id);
            result = new ResultResponse<>(200,"查询仓库和库位成功",warehouseAndStorage);

        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询仓库和库位失败,请稍后再试");
        }

        return result;
    }


    @GetMapping("/querySalableStock")
    @ApiOperation(value = "查询可出库存,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pcode",value = "根据产品编码查找",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pname",value = "根据产品名查找",dataType = "String",paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseMoveVo>> querySalableStock(@RequestParam(value = "now",defaultValue = "1") int now,
                                                                     @RequestParam(value = "size",defaultValue = "3") int size, String pcode, String pname){
        ResultResponse<PageInfo<WarehouseMoveVo>> result = null;

        try {
            PageInfo<WarehouseMoveVo> pageInfo = warehouseMoveService.querySalableStockByPage(now, size, pcode,pname);
            result = new ResultResponse<>(200,"查询可出库存成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询可出库存失败,等下再来试试");
        }
        return result;
    }



    @GetMapping("/queryFinancialCategory")
    @ApiOperation(value = "查询财务类别,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "ftname",value = "根据财务类别名称查找",dataType = "String",paramType = "query")
    })
    public ResultResponse<PageInfo<WarehouseMoveVo>> queryFinancialCategory(@RequestParam(value = "now",defaultValue = "1") int now,
                                                                       @RequestParam(value = "size",defaultValue = "3") int size, String ftname){
        ResultResponse<PageInfo<WarehouseMoveVo>> result = null;

        try {
            PageInfo<WarehouseMoveVo> pageInfo = warehouseMoveService.queryFinancialCategoryByPage(now, size, ftname);
            result = new ResultResponse<>(200,"查询财务类别数据成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询财务类别数据失败,等下再来试试");
        }
        return result;
    }


    @GetMapping("/queryMoveNumCount")
    @ApiOperation(value = "查询移库次数")
    public ResultResponse<WarehouseMoveVo> queryFinancialCategory(){
        ResultResponse<WarehouseMoveVo> result = null;

        try {
            WarehouseMoveVo numCount = warehouseMoveService.queryMoveNumCount();
            result = new ResultResponse<>(200,"查询移库次数成功",numCount);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询移库次数失败,等下再来试试");
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
        String name = "移库报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<WarehouseMoveVo> list = warehouseMoveService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), WarehouseMoveVo.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }


    @GetMapping("/downloadBySalableStock")
    public void downloadBySalableStock(@RequestParam(value = "ids") int[] ids, HttpServletResponse response) throws IOException {

        if (ids.length < 1) {
            return;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String name = "可出库存报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<SalableStockVo> list = warehouseMoveService.queryBySalableStockIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), SalableStockVo.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }









}
