package com.mind.mind_warehouse.entity;

public class WarehouseInOutSale {
    private Integer id;

    private String warehouse;

    private Integer relatedNum;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public Integer getRelatedNum() {
        return relatedNum;
    }

    public void setRelatedNum(Integer relatedNum) {
        this.relatedNum = relatedNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}