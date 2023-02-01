package com.mind.mind_warehouse.vo;

import lombok.Data;

import java.util.Date;

@Data
public class QuarterVo {

    private Integer productId;

    private String productCode;

    private String productName;

    private String unitName;

    private String productType;

    private String warehouseName;

    private Integer stock;

    private Integer inNum;//入库的数量

    private Integer outNum;//出库的数量

    private Date changeUpdateTime;//出入库改变时间



}