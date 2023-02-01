package com.mind.mind_warehouse.entity;

public class WarehouseSaleFinance {
    private Integer id;

    private String financeNum;

    private String relatedNum;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinanceNum() {
        return financeNum;
    }

    public void setFinanceNum(String financeNum) {
        this.financeNum = financeNum == null ? null : financeNum.trim();
    }

    public String getRelatedNum() {
        return relatedNum;
    }

    public void setRelatedNum(String relatedNum) {
        this.relatedNum = relatedNum == null ? null : relatedNum.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}