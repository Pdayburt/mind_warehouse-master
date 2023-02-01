package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Logistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logistics record);

    Logistics selectByPrimaryKey(Integer id);

    List<Logistics> selectAll();

    int updateByPrimaryKey(Logistics record);

    List<Logistics> selectLogByCons(@Param("code") String code,@Param("id") Integer id);

    int deleteBatchKeys(int[] ids);
}