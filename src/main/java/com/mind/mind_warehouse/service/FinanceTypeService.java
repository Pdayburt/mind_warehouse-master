package com.mind.mind_warehouse.service;


import com.mind.mind_warehouse.entity.FinanceType;
import com.mind.mind_warehouse.mapper.FinanceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceTypeService {

    @Autowired
    FinanceTypeMapper financeTypeMapper;



    public List<FinanceType> queryAll(){
        return financeTypeMapper.selectAll();
    }

}
