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
        //1.添加报损信息
        int i = warehouseDamageMapper.insertWarehouseDamage(warehouseDamageVo);
        System.out.println(warehouseDamageVo.getProductId()+"==============getProductId================");

        //2.根据产品id 查询库存表id
        int stockId = warehouseDamageMapper.selectStockId(warehouseDamageVo.getProductId());

        //====可以合并成一步 ---根据产品id查询库存表库存数  但是懒得改了

        //3.根据库存id查询库存数
        int stock = productStockMapper.selectStockById(stockId);
        Integer damageNum = warehouseDamageVo.getDamageNum();
        stock = stock-damageNum;

        //4.根据库存表id做更新操作
        productStockMapper.updateProductStock(stockId,stock);
        return i;

    }


    @Transactional
    public int modifyWarehouseDamage(WarehouseDamageVo warehouseDamageVo){
        //修改报损信息
        int i = warehouseDamageMapper.updateWarehouseDamageByKey(warehouseDamageVo);

        //根据产品id 查询库存表id
        int stockId = warehouseDamageMapper.selectStockId(warehouseDamageVo.getProductId());

        //根据库存id查询库存数
        int stock = productStockMapper.selectStockById(stockId);
        //报损表原报损数
        Integer damageNum1 = warehouseDamageVo.getDamageNum1();
        //要修改的报损数
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
