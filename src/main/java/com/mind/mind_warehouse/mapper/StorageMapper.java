package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Storage;
import com.mind.mind_warehouse.vo.StorageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    Storage selectByPrimaryKey(Integer id);

    List<Storage> selectAll();

    List<StorageVo> selectOnPageByCondition(@Param("code")String code, @Param("name")String name, @Param("warehouseId")Integer warehouseId);

    int updateByPrimaryKey(Storage record);
}