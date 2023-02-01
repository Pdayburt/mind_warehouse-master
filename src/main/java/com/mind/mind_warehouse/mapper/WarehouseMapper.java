package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.vo.WarehouseInfoVo;
import com.mind.mind_warehouse.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Warehouse record);

    Warehouse selectByPrimaryKey(Integer id);

    List<Warehouse> selectAll();

    int updateByPrimaryKey(Warehouse record);

    List<WarehouseInfoVo> selectOnPageByCondition(@Param("code") String code, @Param("name") String name, @Param("departmentId") int departmentId, @Param("type") int type);

    List<WarehouseVo> selectAllWarehouseAndStroage();

    List<WarehouseInfoVo> selectByIds(@Param("ids") int[] ids);
}