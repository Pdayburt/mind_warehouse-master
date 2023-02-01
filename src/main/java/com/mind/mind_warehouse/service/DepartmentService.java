package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Department;
import com.mind.mind_warehouse.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public PageInfo<Department> queryAllPermissionByNameAndPage(Integer pageNum, Integer pageSize, String name){
        PageHelper.startPage(pageNum,pageSize);
        List<Department> departments = departmentMapper.selectDepartmentByName(name);
        return new PageInfo<>(departments);
    }
    public int add(Department department){
        return departmentMapper.insert(department);
    }
    public int removeByPrimaryKey(Integer id){
        return departmentMapper.deleteByPrimaryKey(id);
    }
    public int modifyByPrimaryKey(Department department){
        return departmentMapper.updateByPrimaryKey(department);
    }
    public List<Department> queryAllDepartment(){
        return departmentMapper.selectAll();
    }

}
