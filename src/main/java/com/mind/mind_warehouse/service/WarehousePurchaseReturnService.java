package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.WarehousePurchaseReturn;
import com.mind.mind_warehouse.mapper.WarehousePurchaseReturnMapper;
import com.mind.mind_warehouse.vo.ProductVo;
import com.mind.mind_warehouse.vo.PurchaseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousePurchaseReturnService {
    @Autowired
    WarehousePurchaseReturnMapper warehousePurchaseReturnMapper;
//    List<PurchaseVo> selectPurchaseVoByCons(@Param("num")Integer num,
//                                            @Param("code")String code,
//                                            @Param("name")String name,
//                                            @Param("status")Integer status);

    public PageInfo<PurchaseVo> queryPurchaseVoByCons(Integer pageNum,Integer pageSize,
                                                      Integer num,String code,String name,Integer status){
        PageHelper.startPage(pageNum,pageSize);
        List<PurchaseVo> purchaseVos = warehousePurchaseReturnMapper.selectPurchaseVoByCons(num, code, name, status);
        return new PageInfo<>(purchaseVos);

    }
//    List<PurchaseVo> selectPurchaseVoWithReturnByCons(@Param("returnNum")Integer num,
//                                                      @Param("num")String code,
//                                                      @Param("name")String name,
//                                                      @Param("status")Integer status);

    public PageInfo<PurchaseVo> queryPurchaseVoByWithReturnCons(Integer pageNum,Integer pageSize,
                                                      Integer returnNum,String code,String name,Integer status){
        PageHelper.startPage(pageNum,pageSize);
        List<PurchaseVo> purchaseVos = warehousePurchaseReturnMapper.selectPurchaseVoWithReturnByCons(returnNum, code,name,status);
        return new PageInfo<>(purchaseVos);

    }

    public int removeById(Integer id){
       return warehousePurchaseReturnMapper.deleteByPrimaryKey(id);
    }
//    int insert(WarehousePurchaseReturn record);
    public int add(WarehousePurchaseReturn record){
       return warehousePurchaseReturnMapper.insert(record);
    }
//    int updateByPrimaryKey(WarehousePurchaseReturn record);
    public int modify(WarehousePurchaseReturn record){
        return warehousePurchaseReturnMapper.updateByPrimaryKey(record);
    }
//    WarehousePurchaseReturn selectByPrimaryKey(Integer id);
    public WarehousePurchaseReturn queryByPrimaryKey(Integer id){
        return warehousePurchaseReturnMapper.selectByPrimaryKey(id);
    }
//    List<PurchaseVo> selectByIds(int[] ids);
    public List<PurchaseVo> queryByIds(int[] ids){
        return warehousePurchaseReturnMapper.selectByIds(ids);
    }



}
