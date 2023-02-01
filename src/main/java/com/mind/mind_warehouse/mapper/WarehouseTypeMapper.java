package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseType;

import java.util.List;

public interface WarehouseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseType record);

    WarehouseType selectByPrimaryKey(Integer id);

    List<WarehouseType> selectAll();

    int updateByPrimaryKey(WarehouseType record);
}