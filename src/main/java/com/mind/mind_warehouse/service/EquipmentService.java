package com.mind.mind_warehouse.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Equipment;
import com.mind.mind_warehouse.entity.Units;
import com.mind.mind_warehouse.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;


    public PageInfo<Equipment> findEquipmentByPage(int now, int size, String code, String name){
        PageHelper.startPage(now,size);
        List<Equipment> list = equipmentMapper.selectEquipmentByCons(code, name);
        return new PageInfo<>(list);
    }

    public List<Equipment> findAllEquipment(){
        return equipmentMapper.selectAll();
    }

    public int removeEquipmentById(int id){
        return equipmentMapper.deleteEquipmentById(id);
    }

    public int addEquipment(Equipment equipment){
        return equipmentMapper.insert(equipment);
    }

    public int modifyEquipment(Equipment equipment){
        return equipmentMapper.updateByPrimaryKey(equipment);
    }

    public Equipment findEquipmentById(int id){
        return equipmentMapper.selectEquipmentById(id);
    }


    public List<Equipment> queryByIds(int[] ids) {
        return equipmentMapper.selectByIds(ids);
    }
}
