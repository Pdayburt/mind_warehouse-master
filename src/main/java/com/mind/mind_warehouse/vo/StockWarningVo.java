package com.mind.mind_warehouse.vo;

public class StockWarningVo {
    private Integer id;
    private String prCode;
    private String prName;
    private String uName;
    private String mwName;
    private Integer stockUpper;
    private Integer stockDown;
    private Integer totalCount;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrCode() {
        return prCode;
    }

    public void setPrCode(String prCode) {
        this.prCode = prCode;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getMwName() {
        return mwName;
    }

    public void setMwName(String mwName) {
        this.mwName = mwName;
    }

    public Integer getStockUpper() {
        return stockUpper;
    }

    public void setStockUpper(Integer stockUpper) {
        this.stockUpper = stockUpper;
    }

    public Integer getStockDown() {
        return stockDown;
    }

    public void setStockDown(Integer stockDown) {
        this.stockDown = stockDown;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}