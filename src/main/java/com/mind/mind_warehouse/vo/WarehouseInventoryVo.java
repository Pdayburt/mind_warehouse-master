package com.mind.mind_warehouse.vo;

import com.mind.mind_warehouse.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class WarehouseInventoryVo {

    private Integer id;

    private String inventoryNum;

    private String createByEmp;

    private String empName;//从前台接收制单人

    private List<Integer> inventoryCount;//从前台接收盘点数量

    private List<Integer> checkedIds;

    private List<Product> products;//用于一对多查询


}
