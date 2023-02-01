package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Permission;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.PermissionService;
import com.mind.mind_warehouse.vo.MenuVo;
import com.mind.mind_warehouse.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @GetMapping("/queryAllMenu")
    public ResultResponse<List<MenuVo>> queryAllMenu(){
        ResultResponse<List<MenuVo>> result=null;
        try {
            List<MenuVo> menuVos = permissionService.queryAllMenu();
            result=new ResultResponse<>(200,"查询所有菜单成功",menuVos);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询所有菜单失败");
        }
        return  result;
    }
    @GetMapping("/queryMenuByName")
    public ResultResponse<List<MenuVo>> queryMenuByName(String name){
        ResultResponse<List<MenuVo>> result=null;
        try {
            List<MenuVo> menuVos = permissionService.queryMenuByName(name);
            result=new ResultResponse<>(200,"查询所有菜单成功",menuVos);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询所有菜单失败");
        }
        return  result;
    }
    @GetMapping("/queryRoleId")
    public ResultResponse<List<Integer>> queryRoleId(@RequestParam("id")Integer id){
        ResultResponse<List<Integer>> result=null;
        try {
            List<Integer> roleIds = permissionService.queryRoleIdByEmpId(id);
            result=new ResultResponse<>(200,"查询角色ID成功",roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询角色ID失败");
        }
        return  result;
    }

    @GetMapping("/queryPermissionId")
    public ResultResponse<List<Integer>> queryPermissionId(@RequestParam("id")Integer id){
        ResultResponse<List<Integer>> result=null;
        try {
            List<Integer> integers = permissionService.queryPermissionIdsByRoleId(id);
            result=new ResultResponse<>(200,"查询角色ID成功",integers);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询角色ID失败");
        }
        return  result;
    }
    @PutMapping("/updateRoleIds")
    public ResultResponse<Void> updateRoleIds(@RequestBody RoleVo roleVo){
        ResultResponse<Void> result=null;
        try {
            permissionService.updateRoleIdByEmpId(roleVo);
            result=new ResultResponse<>(200,"更新角色ID成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "更新角色ID失败");
        }
        return  result;
    }
    @PutMapping("/updatePermissionIds")
    public ResultResponse<Void> updatePermissionIds(@RequestBody RoleVo roleVo){
        ResultResponse<Void> result=null;
        try {
            permissionService.updatePermissionByRoleID(roleVo);
            result=new ResultResponse<>(200,"更新权限ID成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "更新权限ID失败");
        }
        return  result;
    }


    @GetMapping("/queryPermissionByName")
    public ResultResponse<PageInfo<Permission>> queryPermissionByName(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                      @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                      String name){
        ResultResponse<PageInfo<Permission>> result=null;
        try {
            PageInfo<Permission> permissionPageInfo = permissionService.queryAllPermissionByNameAndPage(pageNum, pageSize, name);
            result=new ResultResponse<>(200,"根据名称权限查询成功", permissionPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"根据名称权限查询失败");
        }
        return result;
    }
    @GetMapping("/queryAllParentPermission")
    public ResultResponse<List<Permission>> queryAllParentPermission(){
        ResultResponse<List<Permission>> result=null;
        try {
            List<Permission> permissions = permissionService.queryAllParentPermission();
            result=new ResultResponse<>(200,"根据名称权限查询成功", permissions);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"根据名称权限查询失败");
        }
        return result;
    }
    @PostMapping("/addPermission")
    public ResultResponse<Permission> addPermission(@RequestBody Permission permission){
        ResultResponse<Permission> result=null;
        try {
            permissionService.add(permission);
            result=new ResultResponse<>(200,"增加权限路径成功",permission);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "增加权限路径失败");
        }
        return  result;
    }
    @DeleteMapping("/removePermissionById")
    public ResultResponse<Void> removePermissionById(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
            permissionService.removeByPrimaryKey(id);
            result=new ResultResponse<>(200,"删除权限路径成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除权限路径失败");
        }
        return  result;
    }
    @PutMapping("/modifyPermission")
    public ResultResponse<Permission> modifyPermission(@RequestBody Permission permission){
        ResultResponse<Permission> result=null;
        try {
            permissionService.modifyByPrimaryKey(permission);
            result=new ResultResponse<>(200,"增加权限路径成功",permission);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "增加权限路径失败");
        }
        return  result;
    }


}
