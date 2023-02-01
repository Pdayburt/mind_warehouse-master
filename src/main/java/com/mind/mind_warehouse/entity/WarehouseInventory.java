package com.mind.mind_warehouse.entity;

import java.util.Date;

public class WarehouseInventory {
    private Integer id;

    private String inventoryNum;

    private String createByEmp;

    private Integer productId;

    private Integer productNum;

    private String batch;

    private Integer unit;

    private Integer storageId;

    private Integer auditEmpId;

    private Date auditTime;

    private Integer status;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
        this.inventoryNum = inventoryNum == null ? null : inventoryNum.trim();
    }

    public String getCreateByEmp() {
        return createByEmp;
    }

    public void setCreateByEmp(String createByEmp) {
        this.createByEmp = createByEmp;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
}