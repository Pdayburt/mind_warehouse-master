package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Logistics;
import com.mind.mind_warehouse.mapper.LogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @author hnarry
 * @date 2022/6/14 16:07
 */

@Service
public class LogisticsService {

    @Autowired
    LogisticsMapper logisticsMapper;
    public PageInfo<Logistics> researchLogByPages(Integer now, Integer size,String code, Integer id) {
        PageHelper.startPage(now,size);
        List<Logistics> list=logisticsMapper.selectLogByCons(code,id);
        return new PageInfo<>(list);
    }

    public int modify(Logistics logistics) {
        logistics.setUpdateTime(new Date());
        return logisticsMapper.updateByPrimaryKey(logistics);
    }

    public int add(Logistics logistics) {
        logistics.setCreateTime(new Date());
        logistics.setCreateTime(new Date());
        return logisticsMapper.insert(logistics);
    }

    public int removeByKey(int id) {
        int deleteItem = logisticsMapper.deleteByPrimaryKey(id);
        return deleteItem;
    }

    public int removeByKeys(int[] ids) {
        return logisticsMapper.deleteBatchKeys(ids);
    }

    public List<Logistics> researchAll() {
        return logisticsMapper.selectAll();
    }
}
