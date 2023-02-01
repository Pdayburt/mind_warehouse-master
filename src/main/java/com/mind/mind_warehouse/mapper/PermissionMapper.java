package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Permission;
import com.mind.mind_warehouse.vo.MenuVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();
    List<Permission> selectPermissionByName(@Param("name") String name);

    int updateByPrimaryKey(Permission record);
    List<MenuVo> selectAllMenu();
    List<MenuVo> selectMenuByName(@Param("name") String name);
    @Select("select role_id from mw_emp_role where emp_id=#{value}")
    List<Integer> selectRoleIdByEmpId(Integer EmpId);
    int insertRoleIDsByEmpId(@Param("empId")Integer empId,@Param("roleIds")List<Integer> roleIds);
    @Delete("delete from mw_emp_role where emp_id=#{value}")
    int deleteRoleIdsByEmpId(Integer empId);
    @Select("select * from mw_permission where parent_id =-1")
    List<Permission> selectAllParentPermission();

}