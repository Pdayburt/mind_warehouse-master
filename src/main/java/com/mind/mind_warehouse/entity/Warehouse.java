package com.mind.mind_warehouse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

public class Warehouse {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "仓库编码", index = 1)
    private String code;

    @ExcelProperty(value = "仓库名称", index = 2)
    private String name;

    @ExcelIgnore
    private Integer typeId;

    @ExcelIgnore
    private Integer departmentId;

    @ExcelProperty(value = "面积", index = 3)
    private String area;

    @ExcelProperty(value = "联系人", index = 4)
    private String contact;

    @ExcelProperty(value = "联系电话", index = 5)
    private String tel;

    @ExcelProperty(value = "地址", index = 6)
    private String address;

    @ExcelIgnore
    private Date createTime;

    @ExcelIgnore
    private Date updateTime;

    @ExcelIgnore
    private Integer isDelete;

    @ExcelProperty(value = "备注", index = 7)
    private String note;

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
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}