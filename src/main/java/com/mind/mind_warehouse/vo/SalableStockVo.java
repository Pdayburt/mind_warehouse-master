package com.mind.mind_warehouse.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalableStockVo {
    @ExcelIgnore
    private Integer psid;
    @ExcelProperty(value = "库存", index = 6)
    private Integer stock;
    @ExcelProperty(value = "产品编码", index = 1)
    private String pcode;
    @ExcelProperty(value = "产品名称", index = 2)
    private String pname;
    @ExcelProperty(value = "产品类别", index = 4)
    private String ptname;
    @ExcelProperty(value = "储存单位", index = 5)
    private String uname;
    @ExcelProperty(value = "产品规格", index = 3)
    private String standards;

}
