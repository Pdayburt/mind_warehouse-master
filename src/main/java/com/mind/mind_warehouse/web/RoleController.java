package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Role;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/queryRoleByName")
    public ResultResponse<PageInfo<Role>> queryRoleByName(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                          @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                          String name){
        ResultResponse<PageInfo<Role>> result= null;
        try {
            PageInfo<Role> rolePageInfo = roleService.queryAllRolesByName(pageNum, pageSize, name);
            result=new ResultResponse<>(200,"按名字查询角色成功",rolePageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "按名字查询角色失败");
        }
        return result;
    }
    @PostMapping("/addRole")
    @ApiOperation(value = "增加角色")
    public ResultResponse<Void> addRole(@ApiParam(value = "接受前端传来的Role对象") @RequestBody Role role){
        ResultResponse<Void> result=null;
        role.setUpdateTime(new Date());
        try {
            int i = roleService.addRole(role);
            result=new ResultResponse<>(200,"增加角色成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<Void>(201,"增加角色失败!");
        }
        return result;
    }

    @DeleteMapping("/deleteRole")
    public ResultResponse<Void> deleteRole(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
            roleService.removeByPrimaryKey(id);
            result=new ResultResponse<>(200,"删除角色成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"删除角色失败!");
        }
        return result;
    }
    @PutMapping("/updateRole")
    public ResultResponse<Role> updateRole(@RequestBody Role role){
        ResultResponse<Role> result=null;
        try {
            roleService.updateById(role);
            result=new ResultResponse<>(200,"更新角色成功!",role);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(200,"更新角色失败!");
        }
        return result;
    }
    @GetMapping("/queryAllRole")
    public ResultResponse<List<Role>> queryRoleByName(){
        ResultResponse<List<Role>> result= null;
        try {
            List<Role> roles = roleService.queryAll();
            result=new ResultResponse<>(200,"查询所有角色成功",roles);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201, "查询所欲角色失败");
        }
        return result;
    }



}
