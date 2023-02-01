package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int deleteById(Integer id);

    List<Supplier> selectByLike(@Param("code") String code,@Param("name") String name,@Param("type") Integer type,@Param("tel") String tel);
}