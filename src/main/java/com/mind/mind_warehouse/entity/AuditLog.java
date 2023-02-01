package com.mind.mind_warehouse.entity;

import java.util.Date;

public class AuditLog {
    private Integer id;

    private String orderNum;

    private Integer orderType;

    private Integer auditOpId;

    private Integer opType;

    private String reason;

    private Integer result;

    private Integer errorType;

    private Date opTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getAuditOpId() {
        return auditOpId;
    }

    public void setAuditOpId(Integer auditOpId) {
        this.auditOpId = auditOpId;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public AuditLog(String orderNum, Integer orderType, Integer auditOpId, Integer opType, String reason) {
        this.orderNum = orderNum;
        this.orderType = orderType;
        this.auditOpId = auditOpId;
        this.opType = opType;
        this.reason = reason;
    }
}