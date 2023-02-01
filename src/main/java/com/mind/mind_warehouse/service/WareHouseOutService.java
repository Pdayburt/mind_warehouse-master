package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.mind.mind_warehouse.entity.WarehouseInOut;
import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.entity.WarehouseOut;
import com.mind.mind_warehouse.mapper.CustomerMapper;
import com.mind.mind_warehouse.mapper.WarehouseOutMapper;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import com.mind.mind_warehouse.vo.WarehouseOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WareHouseOutService {

    @Autowired
    WarehouseOutMapper warehouseOutMapper;

    @Autowired
    CustomerMapper customerMapper;


    public PageInfo<WarehouseOutVo> researchWareHouseOutByPages(Integer now, Integer size, String orderNum, String cTel) {
        PageHelper.startPage(now,size);
        List<WarehouseOutVo> olist=warehouseOutMapper.selectWareOutByCons(orderNum,cTel);
        return new PageInfo<>(olist);
    }


    @Transactional
    public int add(WarehouseOutVo warehouseOutVo) {
//新增出库单号
        WarehouseOut warehouseOut =new WarehouseOut(warehouseOutVo.getType(),warehouseOutVo.getOrderNum(),warehouseOutVo.getCustomerCode(),warehouseOutVo.getCreateTime(),warehouseOutVo.getUpdateTime(),warehouseOutVo.getIsDelete());

        String outcode = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_OUT_CODE);
        warehouseOut.setOrderNum(outcode);
        WarehouseInOut warehouseInOut = new WarehouseInOut();

        warehouseOutVo.getWarehouseInOut().setChangeId(outcode);
//  新增产品表
        WarehouseInOutSub  warehouseInOutSub= new WarehouseInOutSub();
        warehouseOut.setCreateTime(new Date());
        warehouseOut.setUpdateTime(new Date());
        warehouseOut.setIsDelete(0);
//新增出库表
        int o=warehouseOutMapper.insert(warehouseOut);
        System.out.println(warehouseOut);

        warehouseOutMapper.insertOutProduct(warehouseOut.getId(),warehouseOutVo.getProductId(),warehouseOutVo.getChangeNum());
//插入中间表
        warehouseOutMapper.insertWarehouseOut(warehouseOutVo);
//        warehouseOutMapper.insertCustomerInfo(warehouseOutVo);
//库存更新
        return o;
    }

    public WarehouseOutVo researchWarehouseOutId(Integer id) {
        return warehouseOutMapper.selectByPrimaryKey(id);
    }


    @Transactional
    public int updateWareHouseOut(WarehouseOutVo warehouseOutVo) {
//        删除出库信息
        System.out.println(warehouseOutVo);
//        删除出库表子表产品信息
        int o = warehouseOutMapper.deleteWareOutAndProduct(warehouseOutVo.getId());
        warehouseOutMapper.insertOutProduct(warehouseOutVo.getId(),warehouseOutVo.getProductId(),warehouseOutVo.getChangeNum());

        return o;
    }

    @Transactional
    public int removeWareOut(int id) {
        int Item = warehouseOutMapper.deleteByPrimaryKey(id);
        warehouseOutMapper.deleteWareOutAndProduct(id);
        return Item;
    }

    public WarehouseOutVo queryAll(Integer id) {
        return  warehouseOutMapper.selectWareListByKey(id);
    }


    public WarehouseMoveVo queryDelivery(){
        return warehouseOutMapper.selectDelivery();
    }

    public WarehouseMoveVo queryReadyForDelivery(){
        return warehouseOutMapper.selectReadyForDelivery();
    }


    public int removeByKeys(int[] ids) {
        int delete =warehouseOutMapper.deleteBatchByKeys(ids);
        warehouseOutMapper.deleteBatchWareIn(ids);

        return  delete;
    }
}

