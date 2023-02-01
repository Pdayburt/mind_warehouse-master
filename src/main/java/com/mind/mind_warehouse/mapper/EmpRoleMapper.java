package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.EmpRole;
import com.mind.mind_warehouse.vo.EmployeeVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmpRole record);

    EmpRole selectByPrimaryKey(Integer id);

    List<EmpRole> selectAll();

    int updateByPrimaryKey(EmpRole record);
    @Delete("delete from mw_emp_role where emp_id=#{value}")
    int deleteByRoleId(Integer id);
    int insertRoleIdsByEmpId(@Param("empId") Integer empId,@Param("RoleIds") List<Integer> RoleIds);


}