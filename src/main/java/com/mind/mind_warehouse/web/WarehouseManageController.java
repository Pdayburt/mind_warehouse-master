package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehouseManageService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.WarehouseInfoVo;
import com.mind.mind_warehouse.vo.WarehouseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("warehouseManage")
@Api(value = "基本仓库管理相关接口")
public class WarehouseManageController {

    @Autowired
    WarehouseManageService wms;

    @PostMapping("/add")
    @ApiOperation(value = "新增仓库")
    public ResultResponse<Void> addWarehouse(@RequestBody Warehouse warehouse) {
        ResultResponse<Void> result;

        try {
            String waCode = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_CODE);
            warehouse.setUpdateTime(new Date());
            warehouse.setCreateTime(new Date());
            warehouse.setCode(waCode);
            warehouse.setIsDelete(0);
            wms.add(warehouse);
            result = new ResultResponse<>(200, "添加仓库成功!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加仓库失败!");
        }

        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改仓库")
    public ResultResponse<Void> modify(@RequestBody Warehouse warehouse) {
        ResultResponse<Void> result;
        try {
            //根据 ID 查询出来 当前数据  把前台传递过来的数据  赋值到新查询出来的对象上
            Warehouse wh = wms.queryById(warehouse.getId());
            if (wh == null) {
                result = new ResultResponse<>(201, "未查找到该仓库信息！");
            } else {
                wh.setName(warehouse.getName());
                wh.setTypeId(warehouse.getTypeId());
                wh.setDepartmentId(warehouse.getDepartmentId());
                wh.setArea(warehouse.getArea());
                wh.setContact(warehouse.getContact());
                wh.setTel(warehouse.getTel());
                wh.setAddress(warehouse.getAddress());
                wh.setNote(warehouse.getNote());
                wh.setUpdateTime(new Date());

                wms.modify(wh);
                result = new ResultResponse<>(200, "修改仓库成功!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "修改仓库失败!");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    public ResultResponse<Void> del(@PathVariable("id") Integer id) {
        ResultResponse<Void> result;

        try {
            Warehouse wh = wms.queryById(id);
            if (wh == null) {
                result = new ResultResponse<>(201, "未查询到仓库信息!");
            } else {
                wh.setIsDelete(1);
                wh.setUpdateTime(new Date());
                wms.modify(wh);
                result = new ResultResponse<>(200, "删除仓库成功!");
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除仓库失败!");
        }
        return result;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询仓库列表")
    public ResultResponse<PageInfo<WarehouseInfoVo>> querylist(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer now,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer size,
            String code,
            String name,
            @RequestParam(value = "departmentId", defaultValue = "0") Integer departmentId,
            @RequestParam(value = "type", defaultValue = "0") Integer type) {
        //integer如果传入null 会报空指针错误，但是string却不会
        ResultResponse<PageInfo<WarehouseInfoVo>> result;

        try {
            PageInfo<WarehouseInfoVo> pageInfo = wms.queryOnPageByCondition(now, size, code, name, departmentId, type);
            result = new ResultResponse<>(200, "查询仓库列表成功!", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询仓库列表失败!");
        }
        return result;
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询获取所有")
    public ResultResponse<List<Warehouse>> queryAll() {

        ResultResponse<List<Warehouse>> result;

        try {
            List<Warehouse> list = wms.queryAll();
            result = new ResultResponse<>(200, "查询仓库列表成功!", list);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询仓库列表失败!");
        }
        return result;
    }

    @GetMapping("/allWarehouseVo")
    @ApiOperation(value = "查询获取所有仓库以及库位")
    public ResultResponse<List<WarehouseVo>> queryAllWarehouseVo() {

        ResultResponse<List<WarehouseVo>> result;

        try {
            List<WarehouseVo> list = wms.queryAllWarehouseVo();
            result = new ResultResponse<>(200, "查询列表成功!", list);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询列表失败!");
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
        String name = "仓库报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<WarehouseInfoVo> list = wms.queryByIds(ids);
        //OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), Warehouse.class).sheet("模板").doWrite(list);
            //outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //outputStream.close();
        }
    }

}
