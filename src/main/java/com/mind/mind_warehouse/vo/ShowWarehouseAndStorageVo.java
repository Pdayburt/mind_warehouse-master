package com.mind.mind_warehouse.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowWarehouseAndStorageVo {
    private Integer id;
    private Integer oldWarehouseId;
    private Integer newWarehouseId;
    private Integer oldStorageId;
    private Integer newStorageId;
    private String newWarehouseName;
    private String oldWarehouseName;
    private String oldStorageName;
    private String newStorageName;


}
