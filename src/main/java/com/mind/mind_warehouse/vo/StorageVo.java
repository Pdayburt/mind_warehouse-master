package com.mind.mind_warehouse.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageVo {
    private Integer id;

    private String code;

    private String name;

    private Integer typeId;

    private Integer warehouseId;

    private Integer isBan;

    private Integer isDefault;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private String note;

    private String typeName;

    private String warehouseName;
}
