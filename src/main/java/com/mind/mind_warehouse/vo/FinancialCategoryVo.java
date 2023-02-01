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
public class FinancialCategoryVo {

    @ExcelIgnore
    private Integer ftid;
    @ExcelProperty(value = "类别编号", index = 1)
    private String ftcode;
    @ExcelProperty(value = "类别名称", index = 2)
    private String ftname;
    @ExcelIgnore
    private String ftnote;
    @ExcelProperty(value = "创建时间", index = 3)
    private Date createTime;
    @ExcelIgnore
    private Date updateTime;
    @ExcelIgnore
    private Integer isDelete;



}
