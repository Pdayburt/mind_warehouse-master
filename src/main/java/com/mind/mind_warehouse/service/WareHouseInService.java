package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.mind.mind_warehouse.entity.WarehouseIn;
import com.mind.mind_warehouse.entity.WarehouseInOut;
import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.mapper.ProductStockMapper;
import com.mind.mind_warehouse.mapper.WarehouseInMapper;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.WareHouseInVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WareHouseInService {
    @Autowired
    WarehouseInMapper warehouseInMapper;

    @Autowired
    ProductStockMapper productStockMapper;

    public PageInfo<WareHouseInVo> researchWareHouseInByPages(Integer now, Integer size, String orderNum, Integer type, Integer status) {
        PageHelper.startPage(now,size);
        List<WareHouseInVo> list= warehouseInMapper.selectWarehouseInByCons(orderNum,type,status);
        return new PageInfo<>(list);
    }

    @Transactional
    public int add(WareHouseInVo wareHouseInVo) {
//        新增入库表
        WarehouseIn warehouseIn = new WarehouseIn(wareHouseInVo.getType(),wareHouseInVo.getOrderNum(),wareHouseInVo.getSupplierId(),wareHouseInVo.getCreateTime(),wareHouseInVo.getUpdateTime(),wareHouseInVo.getIsDelete());
        String incode = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_IN_CODE);
        warehouseIn.setOrderNum(incode);

        WarehouseInOut warehouseInOut = new WarehouseInOut();
        wareHouseInVo.setChangeId(incode);
//        warehouseInOut.setChangeId(incode);

        WarehouseInOutSub warehouseInOutSub = new WarehouseInOutSub();
        warehouseIn.setCreatTime(new Date());
        warehouseIn.setUpdateTime(new Date());
        warehouseIn.setIsDelete(0);

        int a =warehouseInMapper.insert(warehouseIn);
        System.out.println(warehouseIn);
//        System.out.println(wareHouseInVo.getId());
//        产品添加入库
        String inBatch = CodeCreateUtil.getBatch();
        wareHouseInVo.setBatch(inBatch);
        warehouseInMapper.insertProduct(warehouseIn.getId(),wareHouseInVo.getProductId(),wareHouseInVo.getChangeNum(),wareHouseInVo.getStorageId(),wareHouseInVo.getBatch());
        warehouseInMapper.insertWareIn(wareHouseInVo);

        return a;
    }
//
    public WareHouseInVo researchWareHouseInId(Integer id) {

        return warehouseInMapper.selectByPrimaryKey(id);

    }


    @Transactional
    public int modifyWarehouseIn(WareHouseInVo wareHouseInVo) {

//删除产品表
        int o = warehouseInMapper.deleteWareIn(wareHouseInVo.getId());

//  删除入库信息
        String inBatch = CodeCreateUtil.getBatch();
        wareHouseInVo.setBatch(inBatch);
        warehouseInMapper.insertProduct(wareHouseInVo.getId(),wareHouseInVo.getProductId(),wareHouseInVo.getChangeNum(),wareHouseInVo.getStorageId(),wareHouseInVo.getBatch());


//  入库库存更新
//
        return o;
    }

    public int removeWareIn(int id) {
//        删除入库表
      int a = warehouseInMapper.deleteByPrimaryKey(id);
        warehouseInMapper.deleteWareIn(id);
        return a;
    }

    public int removeByKeys(int[] ids) {
        int delete =warehouseInMapper.deleteBatchByKeys(ids);
        warehouseInMapper.deleteBatchWareIn(ids);

        return  delete;
    }
}
