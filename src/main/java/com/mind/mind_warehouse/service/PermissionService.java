package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Permission;
import com.mind.mind_warehouse.mapper.PermissionMapper;
import com.mind.mind_warehouse.mapper.RolePermissionMapper;
import com.mind.mind_warehouse.vo.MenuVo;
import com.mind.mind_warehouse.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    public List<MenuVo> queryAllMenu(){
        return permissionMapper.selectAllMenu();
    }
//    List<Integer> selectRoleIdByEmpId(Integer EmpId);
    public List<Integer> queryRoleIdByEmpId(Integer EmpId){
        return permissionMapper.selectRoleIdByEmpId(EmpId);
    }
    @Transactional
    public int updateRoleIdByEmpId(RoleVo roleVo){
        try {
            permissionMapper.deleteRoleIdsByEmpId(roleVo.getId());
            permissionMapper.insertRoleIDsByEmpId(roleVo.getId(), roleVo.getCheckedIds());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
//    List<Permission> selectAll();
    public PageInfo<Permission> queryAllPermissionByNameAndPage(Integer pageNum, Integer pageSize, String name){
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> permissions = permissionMapper.selectPermissionByName(name);
        return new PageInfo<>(permissions);
    }
//    List<Permission> selectAllParentPermission();

    public List<Permission> queryAllParentPermission (){
        return permissionMapper.selectAllParentPermission();
    }
//    int insert(Permission record);
    public int add(Permission permission){
        return permissionMapper.insert(permission);
    }
//    int deleteByPrimaryKey(Integer id);
    public int removeByPrimaryKey(Integer id){
        return permissionMapper.deleteByPrimaryKey(id);
    }
//    int updateByPrimaryKey(Permission record);
    public int modifyByPrimaryKey(Permission permission){
        return permissionMapper.updateByPrimaryKey(permission);
    }
//    List<MenuVo> selectMenuByName(@Param("name") String name);
    public List<MenuVo> queryMenuByName(String name){
        return permissionMapper.selectMenuByName(name);
    }
    @Autowired
    RolePermissionMapper rolePermissionMapper;
//    List<Integer> selectPermissionByRoleId(Integer roleId);
    public List<Integer> queryPermissionIdsByRoleId(Integer roleId){
        return rolePermissionMapper.selectPermissionIdsByRoleId(roleId);
    }
//    int insertPermissionIdByRoleId(@Param("roleId")Integer roleId,@Param("permissionId")List<Integer> permissionId);

    public int addPermissionIdByRoleId(Integer roleId,List<Integer> permissionId){
        return rolePermissionMapper.insertPermissionIdByRoleId(roleId,permissionId);
    }
    @Transactional
    public int updatePermissionByRoleID(RoleVo roleVo){
        try {
            rolePermissionMapper.deleteByRoleId(roleVo.getId());
            rolePermissionMapper.insertPermissionIdByRoleId(roleVo.getId(),roleVo.getCheckedIds());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
