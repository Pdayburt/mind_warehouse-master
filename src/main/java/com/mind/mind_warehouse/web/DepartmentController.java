package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Department;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/queryDepartmentByName")
    public ResultResponse<PageInfo<Department>> queryPermissionByName(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                      @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                      String name){
        ResultResponse<PageInfo<Department>> result=null;
        try {
            PageInfo<Department> departmentPageInfo = departmentService.queryAllPermissionByNameAndPage(pageNum, pageSize, name);
            result=new ResultResponse<>(200,"根据名称查询部门成功", departmentPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"根据名称查询部门失败");
        }
        return result;
    }

    @PostMapping("/addDepartment")
    public ResultResponse<Department> addPermission(@RequestBody Department department){
        ResultResponse<Department> result=null;
        try {
            int add = departmentService.add(department);
            result=new ResultResponse<>(200,"增加部门成功",department);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "增加部门失败");
        }
        return  result;
    }
    @DeleteMapping("/removeDepartmentById")
    public ResultResponse<Void> updateRoleIds(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
            departmentService.removeByPrimaryKey(id);
            result=new ResultResponse<>(200,"删除部门成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除部门失败");
        }
        return  result;
    }
    @PutMapping("/modifyDepartment")
    public ResultResponse<Department> modifyPermission(@RequestBody Department department){
        ResultResponse<Department> result=null;
        try {
            departmentService.modifyByPrimaryKey(department);
            result=new ResultResponse<>(200,"增加部门成功",department);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "增加部门失败");
        }
        return  result;
    }
    @GetMapping("/queryAllDepartment")
    public ResultResponse<List<Department>> queryAllDepartment(){
        ResultResponse<List<Department>> result;
        try {
            List<Department> departments = departmentService.queryAllDepartment();
            result=new ResultResponse<>(200,"查询所有部门成功", departments);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(200,"查询所有部门成功");
        }
        return result;
    }
}

