package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.mapper.StockWarningMapper;
import com.mind.mind_warehouse.vo.StockWarningVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockWarningService {
    @Autowired
    StockWarningMapper stockWarningMapper;
    public PageInfo<StockWarningVo> researchStockWarnByPages(Integer now, Integer size, String prCode, String prName, Integer id) {
        PageHelper.startPage(now,size);
        List<StockWarningVo> list = stockWarningMapper.selectProductStockWarnByCons(prCode,prName,id);
        return new PageInfo<>(list);
    }
}