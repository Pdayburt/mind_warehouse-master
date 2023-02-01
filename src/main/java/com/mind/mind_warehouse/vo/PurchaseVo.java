package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;

@Data
public class PurchaseVo {
    @ExcelIgnore
    private int purchaseId;
    @ExcelProperty(value = "产品编号")
    private int purchaseNum;
    @ExcelProperty(value = "产品名")
    private String productName;
    @ExcelIgnore
    private String productCode;
    @ExcelProperty(value = "产品规格")
    private String productStandards;
    @ExcelIgnore
    private int productStoreUnitId;
    @ExcelProperty(value = "单价")
    private BigDecimal productPrice;
    @ExcelProperty(value = "购买数量")
    private int purchaseCount;
    @ExcelIgnore
    private String purchaseReturnNum;
    @ExcelIgnore
    private String supplierCode;
    @ExcelProperty(value = "供应商")
    private String supplierName;
    @ExcelIgnore
    private int purchaseStatus,purchaseEnterFundStatus;
}
