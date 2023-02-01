package com.mind.mind_warehouse.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDamageVo {
    @ExcelIgnore
    private Integer wdid;
    @ExcelIgnore
    private Integer psid;
    @ExcelIgnore
    private Integer productId;
    @ExcelProperty(value = "报损单号", index = 1)
    private String wdcode;
    @ExcelIgnore
    private String wdnote;
    @ExcelIgnore
    private String ptname;
    @ExcelIgnore
    private String uname;
    @ExcelIgnore
    private Double price;
    @ExcelIgnore
    private Double weight;
    @ExcelProperty(value = "批次", index = 4)
    private String batch;
    @ExcelProperty(value = "报损数量", index = 6)
    private Integer damageNum;
    @ExcelIgnore
    private Integer damageNum1;
    @ExcelIgnore
    private Integer packageType;
    @ExcelIgnore
    private Integer warehouseId;
    @ExcelIgnore
    private Integer oldStorageId;
    @ExcelIgnore
    private Integer storageId;
    @ExcelIgnore
    private Integer damageStorageId;
    @ExcelIgnore
    private Integer stock;
    @ExcelProperty(value = "产品名称", index = 2)
    private String pname;
    @ExcelProperty(value = "产品编号", index = 3)
    private String pcode;
    @ExcelProperty(value = "规格", index = 5)
    private String standards;
    @ExcelProperty(value = "仓库", index = 7)
    private String wname;
    @ExcelProperty(value = "原库位", index = 8)
    private String osname;
    @ExcelProperty(value = "报损库位", index = 9)
    private String nsname;
    @ExcelIgnore
    private String sname;
    @ExcelProperty(value = "报损类型", index = 10)
    private Integer wdtype;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;

}
