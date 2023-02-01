package com.mind.mind_warehouse.entity;

import java.util.Date;

public class FinanceType {
    private Integer id;
    private Integer isDeleate;

    private String code;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Byte isDelete;

    private String note;


    public FinanceType(Integer isDeleate) {
        this.isDeleate = isDeleate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsDeleate() {
        return isDeleate;
    }

    public void setIsDeleate(Integer isDeleate) {
        this.isDeleate = isDeleate;
    }

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}