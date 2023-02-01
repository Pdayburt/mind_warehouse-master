package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Role;
import com.mind.mind_warehouse.mapper.EmpRoleMapper;
import com.mind.mind_warehouse.mapper.RoleMapper;
import com.mind.mind_warehouse.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    EmpRoleMapper empRoleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
//    List<Role> selectAllRolesByName(String name);
   public PageInfo<Role> queryAllRolesByName(Integer pageNum,Integer pageSize,String name){
       PageHelper.startPage(pageNum,pageSize);
       List<Role> roles = roleMapper.selectAllRolesByName(name);
       return new PageInfo<>(roles);
   }
    public int addRole(Role record){
        return roleMapper.insert(record);
    }
    public Role queryRoleById(Integer id){
        return roleMapper.selectByPrimaryKey(id);
    }

//    int deleteByPrimaryKey(Integer id);

    @Transactional
    public int removeByPrimaryKey(Integer id){
        try {
            roleMapper.deleteByPrimaryKey(id);
            empRoleMapper.deleteByRoleId(id);
            rolePermissionMapper.deleteByRoleId(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int updateById(Role role){
       return roleMapper.updateByPrimaryKey(role);
    }
//    List<Role> selectAll();

    public List<Role> queryAll(){
       return roleMapper.selectAll();
    }

}
