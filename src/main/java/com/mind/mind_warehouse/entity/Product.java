package com.mind.mind_warehouse.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
@ToString
public class Product implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private String firmCode;

    private String innerCode;
//    private String standardsUnit;
    private String standardsUnitId;
    private String standards;

    private Integer storeUnitId;
    private Integer typeId;
    private Integer pakageType;

    private BigDecimal price;

    private Integer storageWarningUpper;

    private Integer storageWarningDown;

    private Long weight;

    private Integer defaultSupplierId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}