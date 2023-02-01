package com.mind.mind_warehouse.entity;

import java.util.Date;

public class WarehouseInOut {
    private Integer id;

    private String changeId;
    private Integer productId;

    private Integer formEmpId;

    private Integer auditEmpId;

    private Date auditTime;

    private Integer status;

    private String relatedOrderNum;

    private String batch;
    private String note;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public Integer getFormEmpId() {
        return formEmpId;
    }

    public void setFormEmpId(Integer formEmpId) {
        this.formEmpId = formEmpId;
    }

    public Integer getAuditEmpId() {
        return auditEmpId;
    }

    public void setAuditEmpId(Integer auditEmpId) {
        this.auditEmpId = auditEmpId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRelatedOrderNum() {
        return relatedOrderNum;
    }

    public void setRelatedOrderNum(String relatedOrderNum) {
        this.relatedOrderNum = relatedOrderNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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


}