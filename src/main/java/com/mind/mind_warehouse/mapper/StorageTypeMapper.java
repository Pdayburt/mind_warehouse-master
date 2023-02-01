package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.StorageType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorageType record);

    StorageType selectByPrimaryKey(Integer id);

    List<StorageType> selectAll();

    int updateByPrimaryKey(StorageType record);
}