package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.mind.mind_warehouse.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeVo {
    @ExcelIgnore
    private int id;
    @ExcelProperty(value = "员工账号")
    private String account;
//    ,password,code,name,tel,dname,email,avatar,creatTime,updateTime,isDelete
@ExcelIgnore
    private String password;
    @ExcelIgnore
    private String code;
    @ExcelProperty(value = "员工姓名")
    private String name;
    @ExcelProperty(value = "员工电话")
    private String tel;
    @ExcelProperty(value = "部门名称")
    private String dname;
    @ExcelProperty(value = "员工邮箱")
    private String email;
    @ExcelIgnore
    private String avatar;
    @ExcelIgnore
    private String creatTime;
    @ExcelIgnore
    private String updateTime;
    @ExcelIgnore
    private String isDelete;
    @ExcelIgnore
    private int status,partmentId;
    @ExcelIgnore
    private List<Integer> roleIds;
    @ExcelIgnore
    private List<Role> roles;
}
