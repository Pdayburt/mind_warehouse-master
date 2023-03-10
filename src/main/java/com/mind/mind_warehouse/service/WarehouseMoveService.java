package com.mind.mind_warehouse.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.mapper.ProductStockMapper;
import com.mind.mind_warehouse.mapper.WarehouseAndStorageMapper;
import com.mind.mind_warehouse.mapper.WarehouseMoveMapper;
import com.mind.mind_warehouse.vo.SalableStockVo;
import com.mind.mind_warehouse.vo.ShowWarehouseAndStorageVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class WarehouseMoveService {

    @Autowired
    WarehouseMoveMapper warehouseMoveMapper;

    @Autowired
    ProductStockMapper productStockMapper;

    @Autowired
    WarehouseAndStorageMapper warehouseAndStorageMapper;




    public PageInfo<WarehouseMoveVo> findWarehouseMoveBuyPage(Integer now,Integer size, String moveNum,Integer moveType){
        PageHelper.startPage(now,size);
        List<WarehouseMoveVo> list = warehouseMoveMapper.selectWarehouseMoveBuyCons(moveNum,moveType);
        return new PageInfo<>(list);
    }


    public PageInfo<WarehouseMoveVo> findProductBuyCons(Integer now,Integer size, String pname,String pcode){
        PageHelper.startPage(now,size);
        List<WarehouseMoveVo> list = warehouseMoveMapper.selectProductBuyCons(pname,pcode);
        return new PageInfo<>(list);
    }


    public PageInfo<WarehouseMoveVo> findAllProduct(Integer now,Integer size, String pname,String pcode){
        PageHelper.startPage(now,size);
        List<WarehouseMoveVo> list = warehouseMoveMapper.selectAllProduct(pname,pcode);
        return new PageInfo<>(list);
    }




    public WarehouseMoveVo findWarehouseMoveById(Integer wwmid){
        return warehouseMoveMapper.selectWarehouseMoveById(wwmid);
    }


    public WarehouseMoveVo findWarehouseMoveById(int wwmid){
        return warehouseMoveMapper.selectWarehouseMoveById(wwmid);
    }




    //@Transactional
    public int removeWarehouseMoveById(int id){
        WarehouseMoveVo warehouseMove = warehouseMoveMapper.selectWarehouseMoveById(id);
        String wname = warehouseMove.getWname();
        String sname = warehouseMove.getSname();
        return warehouseMoveMapper.deleteWarehouseMoveById(id);
    }


    //??????
    @Transactional
    public int add(WarehouseMoveVo warehouseMoveVo){

        //1.?????????????????????
        int i = warehouseMoveMapper.insert(warehouseMoveVo);
        //System.out.println(warehouseMoveVo.getId()+"=======================");

        Integer psid = warehouseMoveMapper.selectStockIdByMoveId(warehouseMoveVo.getId());
        //System.out.println(psid+"psid==========");


        //2.??????????????????????????????????????????????????????????????????
        //WarehouseMoveVo w = warehouseMoveMapper.selectWarehouseMoveBuyId(warehouseMoveVo.getId());
        //warehouseMoveVo.setOldWarehouseId(w.getPsWarehouseId());
        //warehouseMoveVo.setOldStorageId(w.getPsProductId());

        //3.????????????????????????????????????
        productStockMapper.updateProductStockById(psid,warehouseMoveVo.getNewWarehouseId(),warehouseMoveVo.getNewStorageId(),new Date());
        return i;
    }

    //??????
    @Transactional
    public int modifyWarehouseMove(WarehouseMoveVo warehouseMoveVo){

        //?????????????????????
        int i = warehouseMoveMapper.update(warehouseMoveVo);

        //?????????id
        Integer newStorageId = warehouseMoveVo.getNewStorageId();
        int pid = warehouseMoveVo.getPid();

        //???????????????id???????????????
        productStockMapper.updateStorageIdByPid(pid,newStorageId,new Date());
        return i;
    }

    public ShowWarehouseAndStorageVo findWarehouseAndStorageById(int id){
        return warehouseAndStorageMapper.selectWarehouseAndStorage(id);
    }


    public PageInfo<WarehouseMoveVo> querySalableStockByPage(Integer now,Integer page,String pcode,String pname){
        PageHelper.startPage(now,page);
        List<WarehouseMoveVo> list = warehouseMoveMapper.selectSalableStockByCons(pcode, pname);
        return new PageInfo<>(list);
    }


    public PageInfo<WarehouseMoveVo> queryFinancialCategoryByPage(Integer now,Integer page,String ftname){
        PageHelper.startPage(now,page);
        List<WarehouseMoveVo> list = warehouseMoveMapper.selectFinancialCategoryByCons(ftname);
        return new PageInfo<>(list);
    }


    //??????????????????
    public WarehouseMoveVo queryMoveNumCount(){
        return warehouseMoveMapper.selectMoveNumCount();
    }

    public List<WarehouseMoveVo> queryByIds(int[] ids){
        return warehouseMoveMapper.selectByIds(ids);
    }


    public List<SalableStockVo> queryBySalableStockIds(int[] ids) {
        return warehouseMoveMapper.selectBySalableStockIds(ids);
    }
}
