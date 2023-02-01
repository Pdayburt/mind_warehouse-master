package com.mind.mind_warehouse.service;

import com.mind.mind_warehouse.mapper.FinanceTypeMapper;
import com.mind.mind_warehouse.vo.FinancialCategoryVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialCategoryService {

    @Autowired
    FinanceTypeMapper financeTypeMapper;


    public int removeFinancialCategoryById(int ftid){
        return financeTypeMapper.deleteByFinancialCategoryId(ftid);
    }


    public int addFinancialCategory(FinancialCategoryVo financialCategoryVo){
        return financeTypeMapper.insertFinancialCategory(financialCategoryVo);
    }

    public int modifyByFinancialCategoryId(FinancialCategoryVo financialCategoryVo){
        return financeTypeMapper.updateByFinancialCategoryId(financialCategoryVo);
    }

    public FinancialCategoryVo queryFinancialCategoryById(int ftid){
        return financeTypeMapper.selectFinancialCategoryById(ftid);
    }


    public List<FinancialCategoryVo> queryByIds(int[] ids) {
        return financeTypeMapper.selectByIds(ids);
    }
}
