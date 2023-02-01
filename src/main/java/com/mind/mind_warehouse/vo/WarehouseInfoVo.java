package com.mind.mind_warehouse.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseInfoVo {
    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "仓库编码", index = 1)
    private String code;

    @ExcelProperty(value = "仓库名称", index = 2)
    private String name;

    @ExcelIgnore
    private Integer typeId;

    @ExcelIgnore
    private Integer departmentId;

    @ExcelProperty(value = "所属部门", index = 3)
    private String dname;

    @ExcelProperty(value = "面积", index = 4)
    private String area;

    @ExcelProperty(value = "联系人", index = 5)
    private String contact;

    @ExcelProperty(value = "联系电话", index = 6)
    private String tel;

    @ExcelProperty(value = "地址", index = 7)
    private String address;

    @ExcelIgnore
    private Date createTime;

    @ExcelIgnore
    private Date updateTime;

    @ExcelIgnore
    private Integer isDelete;

    @ExcelProperty(value = "备注", index = 8)
    private String note;
}
