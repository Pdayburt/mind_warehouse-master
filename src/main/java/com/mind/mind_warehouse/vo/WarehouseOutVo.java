package com.mind.mind_warehouse.vo;


import com.mind.mind_warehouse.entity.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class WarehouseOutVo implements Serializable {
    private Integer id;
    private Integer productId;
    private Integer type;
    private String customerCode;
    private Customer customer;
    private WarehouseInOut warehouseInOut;
    private Integer inOutId;
    private WarehouseInOutSub warehouseInOutSub;
    private WarehouseOut warehouseOut;
    private String batch;
    private String orderNum;
    private String orderType;
    private String cname;
    private String cCode;
    private String cEmail;
    private String pCode;
    private String pName;
    private Integer changeNum;
    private BigDecimal price;
    private String email;
    private String tel;
    private Date updateTime;
    private String cTel;
    private String address;
    private String contact;
    private String cFax;

    private String ename;
    private String aName;


    private Product product;

    private String standardsUnitId;
    private Integer isDelete;
    private Integer sid;
    private Integer status;


    private String changeId;
    private Date CreateTime;
    private Date auditTime;

    private BigDecimal totalPrice;
    private Integer formEmpId;
    private Integer auditEmpId;
//    private List<Product> products;
    //    选择的产品Id放入在集合中


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WarehouseInOut getWarehouseInOut() {
        return warehouseInOut;
    }

    public void setWarehouseInOut(WarehouseInOut warehouseInOut) {
        this.warehouseInOut = warehouseInOut;
    }

    public Integer getInOutId() {
        return inOutId;
    }

    public void setInOutId(Integer inOutId) {
        this.inOutId = inOutId;
    }

    public WarehouseInOutSub getWarehouseInOutSub() {
        return warehouseInOutSub;
    }

    public void setWarehouseInOutSub(WarehouseInOutSub warehouseInOutSub) {
        this.warehouseInOutSub = warehouseInOutSub;
    }

    public WarehouseOut getWarehouseOut() {
        return warehouseOut;
    }

    public void setWarehouseOut(WarehouseOut warehouseOut) {
        this.warehouseOut = warehouseOut;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getcTel() {
        return cTel;
    }

    public void setcTel(String cTel) {
        this.cTel = cTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getcFax() {
        return cFax;
    }

    public void setcFax(String cFax) {
        this.cFax = cFax;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStandardsUnitId() {
        return standardsUnitId;
    }

    public void setStandardsUnitId(String standardsUnitId) {
        this.standardsUnitId = standardsUnitId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public WarehouseOutVo() {
    }
    //    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


    public WarehouseOutVo(Integer id, Integer productId, Integer type, String customerCode, Customer customer, WarehouseInOut warehouseInOut, Integer inOutId, WarehouseInOutSub warehouseInOutSub, WarehouseOut warehouseOut, String batch, String orderNum, String orderType, String cname, String cCode, String cEmail, String pCode, String pName, Integer changeNum, BigDecimal price, String email, String tel, Date updateTime, String cTel, String address, String contact, String cFax, String ename, String aName, String standardsUnitId, Integer isDelete, Integer sid, Integer status, String changeId, Date createTime, Date auditTime, BigDecimal totalPrice, Integer formEmpId, Integer auditEmpId) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.customerCode = customerCode;
        this.customer = customer;
        this.warehouseInOut = warehouseInOut;
        this.inOutId = inOutId;
        this.warehouseInOutSub = warehouseInOutSub;
        this.warehouseOut = warehouseOut;
        this.batch = batch;
        this.orderNum = orderNum;
        this.orderType = orderType;
        this.cname = cname;
        this.cCode = cCode;
        this.cEmail = cEmail;
        this.pCode = pCode;
        this.pName = pName;
        this.changeNum = changeNum;
        this.price = price;
        this.email = email;
        this.tel = tel;
        this.updateTime = updateTime;
        this.cTel = cTel;
        this.address = address;
        this.contact = contact;
        this.cFax = cFax;
        this.ename = ename;
        this.aName = aName;
        this.standardsUnitId = standardsUnitId;
        this.isDelete = isDelete;
        this.sid = sid;
        this.status = status;
        this.changeId = changeId;
        CreateTime = createTime;
        this.auditTime = auditTime;
        this.totalPrice = totalPrice;
        this.formEmpId = formEmpId;
        this.auditEmpId = auditEmpId;
    }
}
