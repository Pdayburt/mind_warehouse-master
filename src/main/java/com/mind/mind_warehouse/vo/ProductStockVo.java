package com.mind.mind_warehouse.vo;

import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.entity.Storage;
import com.mind.mind_warehouse.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockVo {
    private Integer id;
    private Integer psid;
    //维护的产品对象
    //private Product product;
    //库存数
    private Integer stock;
    //维护的仓库对象
    //private Warehouse warehouse;
    //维护的仓储对象
    //private Storage storage;

    private Integer warehouseId;
    private Integer storageId;


    private Date createTime;
    private Date updateTime;
    private Integer isDelete;



}
