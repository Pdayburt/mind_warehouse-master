package com.mind.mind_warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStock {
    private Integer id;
    private Integer productId;

    private Integer stock;
    private Integer lockStock;

    private Integer warehouseId;
    private Integer storageId;


    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
