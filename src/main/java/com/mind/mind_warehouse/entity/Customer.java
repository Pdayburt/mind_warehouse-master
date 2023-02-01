package com.mind.mind_warehouse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

public class Customer {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty(value = "顾客编码")
    private String code;
    @ExcelProperty(value = "客户名称")
    private String name;
    @ExcelProperty(value = "电话")
    private String tel;
    @ExcelProperty(value = "传真")
    private String fax;
    @ExcelProperty(value = "邮箱")
    private String email;
    @ExcelProperty(value = "联系人")
    private String contact;
    @ExcelProperty(value = "地址")
    private String address;
    @ExcelProperty(value = "创建时间")
    private Date creatTime;
    @ExcelProperty(value = "更新时间")
    private Date updateTime;
    @ExcelIgnore
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }


}