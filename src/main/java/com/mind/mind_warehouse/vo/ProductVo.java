package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ProductVo implements Serializable {
    @ExcelIgnore
    private int id;
    @ExcelProperty(value = "产品编号")
    private String productCode;
    @ExcelProperty(value = "产品名称")
    private String productName;
    @ExcelIgnore
    private String firmCode;
    @ExcelIgnore
    private String innerCode;
    @ExcelProperty(value = "产品类别")
    private String productTypeName;
    @ExcelProperty(value = "单位")
    private String unitName;
    @ExcelIgnore
    @ExcelProperty(value = "打包类型")
    private String packageTypeName;
    @ExcelIgnore
    private int storageWarningUpper;
    @ExcelIgnore
    private int storageWarningDown;
    @ExcelIgnore
    private int unitId;
    @ExcelIgnore
    private int packageType;
    @ExcelProperty(value = "单价")
    private BigDecimal price;
    @ExcelIgnore
    private BigDecimal weight;
    @ExcelProperty(value = "供应商")
    private String supplierName;



}
