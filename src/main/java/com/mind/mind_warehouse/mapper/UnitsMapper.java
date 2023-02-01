package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Units;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Units record);

    Units selectByPrimaryKey(Integer id);

    List<Units> selectAll();

    int updateByPrimaryKey(Units record);

    @Update("update mw_units set is_delete = '1' where id = #{id}")
    int deleteByPrimaryId(int id);


    @Select("select * from mw_units where id = #{id}")
    Units selectUnitsById(int id);


    List<Units> selectUnitsByCons(@Param("code") String code,@Param("name") String name);
}