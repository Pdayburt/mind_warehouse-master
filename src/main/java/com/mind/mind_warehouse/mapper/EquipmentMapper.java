package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Equipment;
import com.mind.mind_warehouse.entity.Units;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    List<Equipment> selectAll();

    int updateByPrimaryKey(Equipment record);

    @Update("update mw_equipment set is_delete = 1 where id = #{id}")
    int deleteEquipmentById(int id);

    @Select("select * from mw_equipment where id = #{id}")
    Equipment selectEquipmentById(int id);

    List<Equipment> selectEquipmentByCons(@Param("code") String code, @Param("name") String name);

    List<Equipment> selectByIds(@Param("ids") int[] ids);
}