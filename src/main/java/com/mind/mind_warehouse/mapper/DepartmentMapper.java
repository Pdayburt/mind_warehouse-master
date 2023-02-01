package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Department;
import com.mind.mind_warehouse.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
    List<Department> selectDepartmentByName(@Param("name") String name);
}