package com.mind.mind_warehouse.vo;

import lombok.Data;

@Data
public class InventoryPlusVo {

    private Integer inventoryId;

    private String batch;

    private Integer productId;

    private String productCode;

    private String productName;

    private String unitName;

    private Integer inventoryCount;

    private Integer stock;

    private Integer storageId;

    private String storageName;
}
