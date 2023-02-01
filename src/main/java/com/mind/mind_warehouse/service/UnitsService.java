package com.mind.mind_warehouse.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Units;
import com.mind.mind_warehouse.mapper.UnitsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitsService {

    @Autowired
    UnitsMapper unitsMapper;


    public List<Units> findAllUnits(){
        return unitsMapper.selectAll();
    }

    public PageInfo<Units> findUnitsByPage(int now,int size,String code,String name){
        PageHelper.startPage(now,size);
        List<Units> list = unitsMapper.selectUnitsByCons(code, name);
        return new PageInfo<>(list);
    }



    public int removeByPrimaryKey(int id){
        return unitsMapper.deleteByPrimaryId(id);
    }


    public int addUnits(Units units){
        return unitsMapper.insert(units);
    }

    public int modifyUnitsByKey(Units units){
        return unitsMapper.updateByPrimaryKey(units);
    }

    public Units findUnitsById(int id){
        return unitsMapper.selectUnitsById(id);
    }


}
