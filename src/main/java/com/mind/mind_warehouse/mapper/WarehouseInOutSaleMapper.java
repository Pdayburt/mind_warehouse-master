package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseInOutSale;
import java.util.List;

public interface WarehouseInOutSaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseInOutSale record);

    WarehouseInOutSale selectByPrimaryKey(Integer id);

    List<WarehouseInOutSale> selectAll();

    int updateByPrimaryKey(WarehouseInOutSale record);
}