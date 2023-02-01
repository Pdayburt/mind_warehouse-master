package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Storage;
import com.mind.mind_warehouse.entity.StorageType;
import com.mind.mind_warehouse.mapper.StorageMapper;
import com.mind.mind_warehouse.mapper.StorageTypeMapper;
import com.mind.mind_warehouse.vo.StorageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageManageService {

    @Autowired

    StorageMapper storageMapper;

    @Autowired
    StorageTypeMapper storageTypeMapper;



    public int add(Storage storage) {
        return storageMapper.insert(storage);
    }

    public int modify(Storage storage) {
        return storageMapper.updateByPrimaryKey(storage);
    }

    public Storage queryById(int id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public PageInfo<StorageVo> queryOnPageByCondition(Integer now, Integer size,
                                                      String code,
                                                      String name,
                                                      Integer warehouseId) {
        PageHelper.startPage(now,size);
        List<StorageVo> list = storageMapper.selectOnPageByCondition(code, name, warehouseId);
        return new PageInfo<>(list);
    }

    public List<StorageType> queryAllType() {
        return storageTypeMapper.selectAll();
    }

    public List<Storage> queryAll() {
        return storageMapper.selectAll();
    }
}
