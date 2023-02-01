package com.mind.mind_warehouse.web;


import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Units;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.UnitsService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("units")
@Api(value = "对计量单位的相关操作")
public class UnitsController {

    @Autowired
    UnitsService unitsService;




    @GetMapping("/initUnits")
    @ApiOperation(value = "计量单位的相关查询,包括分页查询/条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "now",value = "查询当前页",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "分页单位,表示当前页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "根据名字模糊查找",dataType = "String",paramType = "query")
    })
    public ResultResponse<PageInfo<Units>> initUnits(@RequestParam(value = "now",defaultValue = "1") int now,
                                                     @RequestParam(value = "size",defaultValue = "3") int size,String code,String name){
        ResultResponse<PageInfo<Units>> result = null;

        try {
            PageInfo<Units> pageInfo = unitsService.findUnitsByPage(now, size, code, name);
            result = new ResultResponse<>(200,"查询计量单位成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询计量单位失败,等下再来试试");
        }
        return result;

    }


    @GetMapping("/showUnits")
    @ApiOperation(value = "查询所有计量单位")
    public ResultResponse<List<Units>> showUnits(){
        ResultResponse<List<Units>> result = null;

        try {
            List<Units> allUnits = unitsService.findAllUnits();
            result = new ResultResponse<>(200,"查询计量单位成功",allUnits);

        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询计量单位失败");
        }
        return result;
    }



    @DeleteMapping("/del{id}")
    @ApiOperation(value = "根据单个id删除计量单位的操作")
    public ResultResponse<Void> del(@PathVariable("id") int id){

        ResultResponse<Void> result = null;
        try {
            unitsService.removeByPrimaryKey(id);
            result = new ResultResponse<>(200,"删除计量单位成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除计量单位失败");
        }
        return result;
    }


    @PostMapping("/add")
    @ApiOperation(value = "计量单位的添加操作/封装对象传递")
    public ResultResponse<Void> add(@RequestBody Units units){
        ResultResponse<Void> result = null;
        try {
            String unCode = CodeCreateUtil.getCode(CodeDirectory.UNITS_CODE);
            units.setCode(unCode);
            units.setCreateTime(new Date());
            units.setUpdateTime(new Date());
            unitsService.addUnits(units);
            result = new ResultResponse<>(200,"添加计量单位成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加计量单位失败");
        }
        return result;
    }


    @PutMapping("/modify")
    @ApiOperation(value = "计量单位的修改操作/封装对象传递")
    public ResultResponse<Void> modify(@RequestBody Units units){
        ResultResponse<Void> result = null;
        try {
            Units u = unitsService.findUnitsById(units.getId());
            u.setName(units.getName());
            u.setNote(units.getNote());
            unitsService.modifyUnitsByKey(units);
            result = new ResultResponse<>(200,"更新计量单位成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"更新计量单位失败");
        }
        return result;
    }













}
