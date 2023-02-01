package com.mind.mind_warehouse.web;


import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Equipment;
import com.mind.mind_warehouse.entity.Units;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.EquipmentService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
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
@RequestMapping("equipment")
@Api(value = "设备管理的相关操作")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;




    @GetMapping("/initEquipment")
    @ApiOperation(value = "设备的相关查询,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "根据名字模糊查找",dataType = "String",paramType = "query")
    })
    public ResultResponse<PageInfo<Equipment>> initEquipment(@RequestParam(value = "now",defaultValue = "1") int now,
                                                             @RequestParam(value = "size",defaultValue = "3") int size, String code, String name){
        ResultResponse<PageInfo<Equipment>> result = null;

        try {
            PageInfo<Equipment> pageInfo = equipmentService.findEquipmentByPage(now, size, code, name);
            result = new ResultResponse<>(200,"查询设备成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询设备失败,等下再来试试");
        }
        return result;
    }



    @GetMapping("/showEquipment")
    @ApiOperation(value = "查询所有设备")
    public ResultResponse<List<Equipment>> showEquipment(){
        ResultResponse<List<Equipment>> result = null;
        try {
            List<Equipment> allEquipment = equipmentService.findAllEquipment();
            result = new ResultResponse<>(200,"查询设备成功",allEquipment);

        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询设备失败");
        }
        return result;
    }



    @DeleteMapping("/del{id}")
    @ApiOperation(value = "根据id删除设备的操作")
    public ResultResponse<Void> del(@PathVariable("id") int id){

        ResultResponse<Void> result = null;
        try {
            equipmentService.removeEquipmentById(id);
            result = new ResultResponse<>(200,"删除设备成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除设备失败");
        }
        return result;
    }


    @PostMapping("/add")
    @ApiOperation(value = "设备的添加操作/封装对象传递")
    public ResultResponse<Void> add(@RequestBody Equipment equipment){
        ResultResponse<Void> result = null;
        try {
            String eqCode = CodeCreateUtil.getCode(CodeDirectory.EQUIPMENT_CODE);
            String acode = CodeCreateUtil.getCode(CodeDirectory.AUTHORIZATION_CODE);
            equipment.setCode(eqCode);
            equipment.setAuthorCode(acode);
            equipment.setCreateTime(new Date());
            equipment.setUpdateTime(new Date());
            equipmentService.addEquipment(equipment);
            result = new ResultResponse<>(200,"添加设备成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加设备失败");
        }
        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "设备的更新操作/封装对象传递")
    public ResultResponse<Void> modify(@RequestBody Equipment equipment){
        ResultResponse<Void> result = null;
        try {
            Equipment e = equipmentService.findEquipmentById(equipment.getId());
            e.setUpdateTime(new Date());
            e.setName(equipment.getName());
            e.setAuthorCode(equipment.getAuthorCode());
            e.setIsAuthor(equipment.getIsAuthor());
            e.setNote(equipment.getNote());
            e.setStatus(equipment.getStatus());

            equipmentService.modifyEquipment(equipment);
            result = new ResultResponse<>(200,"更新设备成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"更新设备失败");
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
        String name = "设备报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<Equipment> list = equipmentService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), Equipment.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }






}
