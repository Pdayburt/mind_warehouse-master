package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.mapper.ProductStockMapper;
import com.mind.mind_warehouse.mapper.WarehouseDamageMapper;
import com.mind.mind_warehouse.vo.ProductStockVo;
import com.mind.mind_warehouse.vo.WarehouseDamageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class WarehouseDamageService {

    @Autowired
    WarehouseDamageMapper warehouseDamageMapper;

    @Autowired
    ProductStockMapper productStockMapper;



    public PageInfo<WarehouseDamageVo> queryWarehouseDamageByPage(Integer now, Integer size,Integer wdtype, String wdcode){
        PageHelper.startPage(now,size);
        List<WarehouseDamageVo> list = warehouseDamageMapper.selectWarehouseDamageByCons(wdtype,wdcode);
        return new PageInfo<>(list);
    }


    public int removeWarehouseDamageByKey(Integer wdid){
        return warehouseDamageMapper.deleteWarehouseDamageByKey(wdid);
    }

    public WarehouseDamageVo queryWarehouseDamageById(Integer wdid){
        return warehouseDamageMapper.selectWarehouseDamageById(wdid);
    }


    public PageInfo<WarehouseDamageVo> queryAllProduct(Integer now, Integer size,String pcode,String pname){
        PageHelper.startPage(now,size);
        List<WarehouseDamageVo> list = warehouseDamageMapper.selectAllProduct(pcode,pname);
        return new PageInfo<>(list);
    }

    public PageInfo<WarehouseDamageVo> queryAllProductPlus(Integer now, Integer size,String pcode,String pname){
        PageHelper.startPage(now,size);
        List<WarehouseDamageVo> list = warehouseDamageMapper.selectAllProductPlus(pcode,pname);
        return new PageInfo<>(list);
    }


    public PageInfo<WarehouseDamageVo> queryProduct(Integer now, Integer size,String pcode,String pname){
        PageHelper.startPage(now,size);
        List<WarehouseDamageVo> list = warehouseDamageMapper.selectProduct(pcode,pname);
        return new PageInfo<>(list);
    }


    @Transactional
    public int addWarehouseDamage(WarehouseDamageVo warehouseDamageVo){
        //1.??????????????????
        int i = warehouseDamageMapper.insertWarehouseDamage(warehouseDamageVo);
        System.out.println(warehouseDamageVo.getProductId()+"==============getProductId================");

        //2.????????????id ???????????????id
        int stockId = warehouseDamageMapper.selectStockId(warehouseDamageVo.getProductId());

        //====????????????????????? ---????????????id????????????????????????  ??????????????????

        //3.????????????id???????????????
        int stock = productStockMapper.selectStockById(stockId);
        Integer damageNum = warehouseDamageVo.getDamageNum();
        stock = stock-damageNum;

        //4.???????????????id???????????????
        productStockMapper.updateProductStock(stockId,stock);
        return i;

    }


    @Transactional
    public int modifyWarehouseDamage(WarehouseDamageVo warehouseDamageVo){
        //??????????????????
        int i = warehouseDamageMapper.updateWarehouseDamageByKey(warehouseDamageVo);

        //????????????id ???????????????id
        int stockId = warehouseDamageMapper.selectStockId(warehouseDamageVo.getProductId());

        //????????????id???????????????
        int stock = productStockMapper.selectStockById(stockId);
        //?????????????????????
        Integer damageNum1 = warehouseDamageVo.getDamageNum1();
        //?????????????????????
        Integer damageNum = warehouseDamageVo.getDamageNum();
        Integer num = damageNum-damageNum1;
        stock = stock-num;
        productStockMapper.updateProductStock(stockId,stock);

        return i;
    }


    public List<WarehouseDamageVo> queryByIds(int[] ids) {
        return warehouseDamageMapper.selectByIds(ids);

    }
}
