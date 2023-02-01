package com.mind.mind_warehouse.mapper;
/**
 * @author hnarry
 * @date 2022/6/14 15:02
 */

import com.mind.mind_warehouse.entity.Admin;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

}