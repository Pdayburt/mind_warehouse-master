package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseMoveVo {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty(value = "移库单号", index = 1)
    private String moveNum;
    //维护的产品对象
    //private Product product;
    //批次
    @ExcelIgnore
    private Integer productId;
    @ExcelProperty(value = "批次", index = 5)
    private String batch;
    @ExcelIgnore
    private Integer oldWarehouseId;
    @ExcelIgnore
    private Integer oldStorageId;
    @ExcelIgnore
    private Integer newWarehouseId;
    @ExcelIgnore
    private Integer newStorageId;

    //维护的仓库对象
    //private Warehouse warehouse;
    //维护的库位对象
    //private Storage storage;
    //private ProductStockVo productStockVo;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;
    @ExcelIgnore
    private Integer isDelete;
    @ExcelIgnore
    private Integer psid;
    @ExcelIgnore
    private Integer stock;
    @ExcelIgnore
    private Integer psProductId;
    @ExcelIgnore
    private Integer psWarehouseId;
    @ExcelProperty(value = "产品名称", index = 2)
    private String pname;
    @ExcelProperty(value = "产品编号", index = 3)
    private String pcode;
    @ExcelIgnore
    private String wname;
    @ExcelIgnore
    private String sname;
    @ExcelProperty(value = "备注", index = 7)
    private String wmnote;
    @ExcelProperty(value = "移库类型", index = 6)
    private Integer moveType;
    @ExcelIgnore
    private String note;
    @ExcelIgnore
    private Integer wmid;
    @ExcelIgnore
    private Integer pid;
    @ExcelIgnore
    private Integer owid;
    @ExcelIgnore
    private Integer osid;
    @ExcelIgnore
    private Integer nsid;
    @ExcelProperty(value = "原仓库", index = 8)
    private String owname;
    @ExcelProperty(value = "原库位", index = 9)
    private String osname;
    @ExcelProperty(value = "新仓库", index = 10)
    private String nwname;
    @ExcelProperty(value = "新库位", index = 11)
    private String nsname;
    @ExcelIgnore
    private String uname;
    @ExcelIgnore
    private String ptname;
    @ExcelProperty(value = "规格", index = 4)
    private String standards;
    @ExcelIgnore
    private BigDecimal price;
    @ExcelIgnore
    private Integer pakageType;
    @ExcelIgnore
    private Integer ftid;
    @ExcelIgnore
    private BigDecimal weight;
    @ExcelIgnore
    private Integer wmproductid;
    @ExcelIgnore
    private Integer moveNumCount;
    @ExcelIgnore
    private Integer delivery;
    @ExcelIgnore
    private Integer readyForDelivery;
    @ExcelIgnore
    private String ftcode;
    @ExcelIgnore
    private String ftname;
    @ExcelIgnore
    private String ftnote;






}
