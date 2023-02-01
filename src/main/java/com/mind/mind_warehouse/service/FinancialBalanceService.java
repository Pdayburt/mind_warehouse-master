package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mind.mind_warehouse.entity.FinanceAccountsReceivePay;
import com.mind.mind_warehouse.mapper.FinanceAccountsReceivePayMapper;
import com.mind.mind_warehouse.vo.FinanceAccountsReceivePayVo;
import com.mind.mind_warehouse.vo.ReceivePayVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FinancialBalanceService {
    @Autowired
    FinanceAccountsReceivePayMapper financeAccountsReceivePayMapper;

//    List<FinanceAccountsReceivePay> selectByCons(@Param("serialNumber")Integer serialNumber,
//                                                 @Param("name")String name,
//                                                 @Param("financeTypeId")Integer financeTypeId,
//                                                 @Param("status")Integer status,
//                                                 @Param("relatedId")Integer relatedId);
    public PageInfo<FinanceAccountsReceivePayVo> queryByCons(Integer pageNum, Integer pageSize, Integer serialNumber,
                                                             String name, Integer financeTypeId, Integer status,
                                                             String customerName){
        System.out.println("Service接受到的status值为："+status);
        PageHelper.startPage(pageNum,pageSize);
        List<FinanceAccountsReceivePayVo> financeAccountsReceivePays = financeAccountsReceivePayMapper.selectByCons(serialNumber, name, financeTypeId, status, customerName);
        return new PageInfo<>(financeAccountsReceivePays);
    }
//    List<FinanceAccountsReceivePayVo> selectPayByCons(@Param("serialNumber")Integer serialNumber,
//                                                      @Param("name")String name,
//                                                      @Param("financeTypeId")Integer financeTypeId,
//                                                      @Param("status")Integer status,
//                                                      @Param("supplierName")String  supplierName);

    public PageInfo<FinanceAccountsReceivePayVo> queryPayByCons(Integer pageNum, Integer pageSize, Integer serialNumber,
                                                             String name, Integer financeTypeId, Integer status,
                                                             String supplierName){
        PageHelper.startPage(pageNum,pageSize);
        List<FinanceAccountsReceivePayVo> financeAccountsReceivePayVos = financeAccountsReceivePayMapper.selectPayByCons(serialNumber, name, financeTypeId, status, supplierName);
        return new PageInfo<>(financeAccountsReceivePayVos);
    }
    public int removeById(Integer id){
        return financeAccountsReceivePayMapper.deleteByPrimaryKey(id);
    }
//    int updateByPrimaryKey(FinanceAccountsReceivePay record);
    public int modify(FinanceAccountsReceivePay record){
        return financeAccountsReceivePayMapper.updateByPrimaryKey(record);
    }
    public int add(FinanceAccountsReceivePay record){
        return financeAccountsReceivePayMapper.insert(record);
    }


    @Transactional
    public PageInfo<ReceivePayVo> selectPayByConsBoth(Integer pageNum, Integer pageSize, Integer serialNumber,
                                                      String name, Integer financeTypeId){
        PageHelper.startPage(pageNum,pageSize);
        List<ReceivePayVo> receivePayVos = financeAccountsReceivePayMapper.selectPayByConsBothCustomer(serialNumber, name, financeTypeId);
        List<ReceivePayVo> receivePayVos1 = financeAccountsReceivePayMapper.selectPayByConsBothSupplier(serialNumber, name, financeTypeId);
        List<ReceivePayVo> list = Lists.newArrayList();
        list.addAll(receivePayVos);
        list.addAll(receivePayVos1);
        return new PageInfo<>(list);
    }
}

//