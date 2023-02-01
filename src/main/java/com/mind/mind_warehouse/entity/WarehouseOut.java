package com.mind.mind_warehouse.entity;

import java.util.Date;

public class WarehouseOut {
    private Integer id;

    private Integer type;

    private String orderNum;

    private String customerCode;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
    private Customer customer;

//    public WarehouseOut(Integer type, String orderNum,String customerCode) {
//
//    }

    public WarehouseOut(Integer type, String orderNum, String customerCode, Date createTime, Date updateTime, Integer isDelete) {
        this.orderNum=orderNum;
        this.type=type;
        this.customerCode=customerCode;
        this.createTime=createTime;
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
        this.orderNum = orderNum;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public WarehouseOut() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}