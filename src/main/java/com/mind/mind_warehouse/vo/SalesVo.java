package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.mind.mind_warehouse.entity.SalesManagement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SalesVo {

    @ExcelIgnore
    private Integer sid;

    @ExcelProperty("-")
    private SalesManagement salesManagement;

    @ExcelProperty("规格")
    private String standards;

    @ExcelIgnore
    private Integer stock;

    @ExcelProperty("产品编号")
    private String pcode;

    @ExcelProperty("产品名称")
    private String pname;

    @ExcelProperty("单位")
    private String uname;

    @ExcelProperty("制单员工")
    private String empName;

    @ExcelProperty("客户名称")
    private String cname;

    @ExcelProperty("客户编号")
    private String customerCode;

    @ExcelProperty("单价")
    private BigDecimal price;

}
