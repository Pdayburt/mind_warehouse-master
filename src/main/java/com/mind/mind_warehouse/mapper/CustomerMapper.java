package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Customer;
import com.mind.mind_warehouse.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    int deleteById(Integer id);

    List<Customer> selectByLike(@Param("code") String code, @Param("name") String name,@Param("tel") String tel);

    List<Customer> selectByIds(@Param("ids") Integer[] ids);
}