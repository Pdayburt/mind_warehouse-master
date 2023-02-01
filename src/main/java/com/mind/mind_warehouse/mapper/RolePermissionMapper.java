package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);
    @Delete("delete from mw_role_permission where role_id=#{value}")
    int deleteByRoleId(Integer id);
    @Select("select permission_id from mw_role_permission where role_id=#{value}")
    List<Integer> selectPermissionIdsByRoleId(Integer roleId);
    int insertPermissionIdByRoleId(@Param("roleId")Integer roleId,@Param("permissionId")List<Integer> permissionId);
}