package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.mapper.StockListMapper;
import com.mind.mind_warehouse.vo.StockListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockListService {

    @Autowired
    StockListMapper stockListMapper;
    public PageInfo<StockListVo> researchStockListByPages(Integer now, Integer size, String pduCode, String pduName, Integer id) {
        PageHelper.startPage(now,size);
        List<StockListVo> list= stockListMapper.selectProductStockListByCons(pduCode,pduName,id);
        return new PageInfo<>(list);
    }
}
