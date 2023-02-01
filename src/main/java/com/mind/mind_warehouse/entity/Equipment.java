package com.mind.mind_warehouse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

public class Equipment {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty(value = "设备编码", index = 1)
    private String code;
    @ExcelProperty(value = "设备名称", index = 2)
    private String name;
    @ExcelProperty(value = "是否授权", index = 3)
    private Integer isAuthor;
    @ExcelProperty(value = "授权标识符", index = 4)
    private String authorCode;
    @ExcelProperty(value = "设备状态", index = 5)
    private Integer status;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;
    @ExcelProperty(value = "是否删除", index = 6)
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
        this.name = name;
    }

    public Integer getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(Integer isAuthor) {
        this.isAuthor = isAuthor;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode == null ? null : authorCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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