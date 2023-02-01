package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseSaleFinance;
import java.util.List;

public interface WarehouseSaleFinanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseSaleFinance record);

    WarehouseSaleFinance selectByPrimaryKey(Integer id);

    List<WarehouseSaleFinance> selectAll();

    int updateByPrimaryKey(WarehouseSaleFinance record);
}