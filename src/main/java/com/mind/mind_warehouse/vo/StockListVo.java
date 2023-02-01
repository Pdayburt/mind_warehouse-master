package com.mind.mind_warehouse.vo;

public class StockListVo {
    private Integer id;
    private String pduCode;
    private String pduName;
    private String Standards;
    private String ptoName;
    private String muName;
    private String mwName;
    private String sgeName;
    private Integer srPosCount;
    private Integer srNegCount;
    private Integer totalCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPduCode() {
        return pduCode;
    }

    public void setPduCode(String pduCode) {
        this.pduCode = pduCode;
    }

    public String getPduName() {
        return pduName;
    }

    public void setPduName(String pduName) {
        this.pduName = pduName;
    }

    public String getStandards() {
        return Standards;
    }

    public void setStandards(String standards) {
        Standards = standards;
    }

    public String getPtoName() {
        return ptoName;
    }

    public void setPtoName(String ptoName) {
        this.ptoName = ptoName;
    }

    public String getMuName() {
        return muName;
    }

    public void setMuName(String muName) {
        this.muName = muName;
    }

    public String getMwName() {
        return mwName;
    }

    public void setMwName(String mwName) {
        this.mwName = mwName;
    }

    public String getSgeName() {
        return sgeName;
    }

    public void setSgeName(String sgeName) {
        this.sgeName = sgeName;
    }

    public Integer getSrPosCount() {
        return srPosCount;
    }

    public void setSrPosCount(Integer srPosCount) {
        this.srPosCount = srPosCount;
    }

    public Integer getSrNegCount() {
        return srNegCount;
    }

    public void setSrNegCount(Integer srNegCount) {
        this.srNegCount = srNegCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
