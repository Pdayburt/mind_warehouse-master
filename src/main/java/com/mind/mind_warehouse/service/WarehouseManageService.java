package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.mapper.WarehouseMapper;
import com.mind.mind_warehouse.vo.WarehouseInfoVo;
import com.mind.mind_warehouse.vo.WarehouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseManageService {
    @Autowired
    WarehouseMapper warehouseMapper;

    public int add(Warehouse warehouse) {
        return warehouseMapper.insert(warehouse);
    }

    public int modify(Warehouse warehouse) {
        return warehouseMapper.updateByPrimaryKey(warehouse);
    }

    public Warehouse queryById(int id) {
        return warehouseMapper.selectByPrimaryKey(id);
    }

    public PageInfo<WarehouseInfoVo> queryOnPageByCondition(Integer now, Integer size,
                                                      String code,
                                                      String name,
                                                      Integer departmentId,
                                                      Integer type) {
        PageHelper.startPage(now,size);
        List<WarehouseInfoVo> list = warehouseMapper.selectOnPageByCondition(code, name, departmentId, type);
        return new PageInfo<>(list);
    }

    public List<Warehouse> queryAll() {
        return warehouseMapper.selectAll();
    }

    public List<WarehouseVo> queryAllWarehouseVo() {
        return warehouseMapper.selectAllWarehouseAndStroage();
    }

    public List<WarehouseInfoVo> queryByIds(int[] ids) {
        return warehouseMapper.selectByIds(ids);
    }
}
