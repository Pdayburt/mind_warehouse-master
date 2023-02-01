package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.SalesReturnManagement;
import java.util.List;

public interface SalesReturnManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesReturnManagement record);

    SalesReturnManagement selectByPrimaryKey(Integer id);

    List<SalesReturnManagement> selectAll();

    int updateByPrimaryKey(SalesReturnManagement record);
}