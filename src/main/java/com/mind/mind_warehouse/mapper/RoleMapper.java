package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    List<Role> selectAllRolesByEmpId(Integer id);
    @Select("select * from mw_role where name like '%${value}%'")
    List<Role> selectAllRolesByName(String name);
 }