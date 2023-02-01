package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseInOut;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;

import java.util.List;

public interface WarehouseInOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseInOut record);

    WarehouseInOut selectByPrimaryKey(Integer id);

    List<WarehouseInOut> selectAll();

    int updateByPrimaryKey(WarehouseInOut record);



}