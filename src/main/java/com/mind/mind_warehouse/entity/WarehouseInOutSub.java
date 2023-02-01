package com.mind.mind_warehouse.entity;


import java.math.BigDecimal;
import java.util.Date;

public class WarehouseInOutSub {
    private Integer id;

    private Integer inOutId;
    private String changeId;
    private Integer productId;

    private Integer changeNum;
    private String batch;
    private BigDecimal totalPrice;

    private Integer warehouseId;

    private Integer storageId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;




    public WarehouseInOutSub(Integer productId, Integer changeNum) {
        this.productId=productId;
        this.changeNum=changeNum;
    }

    public WarehouseInOutSub(String batch) {
        this.batch=batch;
    }

    public WarehouseInOutSub(Integer inOutId, String changeId,Integer productId, Integer changeNum, BigDecimal totalPrice, Integer wareHouseId, Integer storageId, Date creatTime, Date updateTime, Integer isDelete, String batch) {
        this.inOutId=inOutId;
        this.changeId=changeId;
        this.productId=productId;
        this.changeNum=changeNum;
        this.totalPrice=totalPrice;
        this.warehouseId=wareHouseId;
        this.storageId=storageId;
        this.createTime=creatTime;
        this.updateTime=updateTime;
        this.isDelete=isDelete;
        this.batch=batch;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInOutId() {
        return inOutId;
    }

    public void setInOutId(Integer inOutId) {
        this.inOutId = inOutId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
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

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public WarehouseInOutSub(Integer id, Integer inOutId, Integer productId, Integer changeNum, String batch, BigDecimal totalPrice, Integer warehouseId, Integer storageId, Date createTime, Date updateTime, Integer isDelete) {
        this.id = id;
        this.inOutId = inOutId;
        this.productId = productId;
        this.changeNum = changeNum;
        this.batch = batch;
        this.totalPrice = totalPrice;
        this.warehouseId = warehouseId;
        this.storageId = storageId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public WarehouseInOutSub() {
    }
}