package com.mind.mind_warehouse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalesManagement {
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("订单编号")
    private String orderNumber;

    @ExcelIgnore
    private Integer productId;

    @ExcelProperty("销售数量")
    private Integer quantity;

    @ExcelProperty("总额")
    private BigDecimal totalPrice;

    @ExcelIgnore
    private Integer customerId;

    @ExcelProperty("订单总价")
    private BigDecimal orderTotalPrice;

    @ExcelProperty("订单状态")
    private Integer status;

    @ExcelProperty("是否支付")
    private Integer isPay;

    @ExcelProperty("联系电话")
    private String tel;

    @ExcelProperty("联系人")
    private String contact;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("发货日期")
    private Date deliverDate;

    @ExcelIgnore
    private Integer type;

    @ExcelProperty("退货单号")
    private String returnOrder;

    @ExcelProperty("退货入库单号")
    private String returnInNumber;

    @ExcelIgnore
    private Integer empId;

    @ExcelProperty("交易流水号")
    private String tradeNo;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("制单日期")
    private Date createTime;

    @ExcelIgnore
    private Date updateTime;

    @ExcelIgnore
    private Integer isDelete;

    @ExcelProperty("备注")
    private String note;

}