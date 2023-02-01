package com.mind.mind_warehouse.entity;

import java.util.Date;

public class WarehouseIn {
    private Integer id;

    private Integer type;

    private String orderNum;

    private Integer supplierId;

    private Date creatTime;

    private Date updateTime;

    private Integer isDelete;

    public WarehouseIn(Integer type, String orderNum, Integer supplierId, Date creatTime, Date updateTime, Integer isDelete) {
    this.type=type;
    this.orderNum=orderNum;
    this.supplierId=supplierId;
    this.creatTime=creatTime;
    this.updateTime=updateTime;
    this.isDelete=isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public WarehouseIn() {
    }


}