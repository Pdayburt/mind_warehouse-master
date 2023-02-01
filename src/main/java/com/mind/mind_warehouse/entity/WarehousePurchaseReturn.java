package com.mind.mind_warehouse.entity;

import java.util.Date;

public class WarehousePurchaseReturn {
    private Integer id;

    private Integer num;

    private Integer type;

    private Integer supplierId;

    private String contactPerson;

    private String tel;

    private Date receiveGoodsTime;

    private Integer createByEmp;

    private Integer productId;

    private Integer count;

    private Long productTotalPrice;

    private String returnNum;

    private Integer enterFundStatus;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Date getReceiveGoodsTime() {
        return receiveGoodsTime;
    }

    public void setReceiveGoodsTime(Date receiveGoodsTime) {
        this.receiveGoodsTime = receiveGoodsTime;
    }

    public Integer getCreateByEmp() {
        return createByEmp;
    }

    public void setCreateByEmp(Integer createByEmp) {
        this.createByEmp = createByEmp;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Long productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum == null ? null : returnNum.trim();
    }

    public Integer getEnterFundStatus() {
        return enterFundStatus;
    }

    public void setEnterFundStatus(Integer enterFundStatus) {
        this.enterFundStatus = enterFundStatus;
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