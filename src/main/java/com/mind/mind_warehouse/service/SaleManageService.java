package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.ProductStock;
import com.mind.mind_warehouse.entity.SalesManagement;
import com.mind.mind_warehouse.mapper.FinanceAccountsReceivePayMapper;
import com.mind.mind_warehouse.mapper.ProductStockMapper;
import com.mind.mind_warehouse.mapper.SalesManagementMapper;
import com.mind.mind_warehouse.mapper.WarehouseInMapper;
import com.mind.mind_warehouse.vo.ProductStockVo;
import com.mind.mind_warehouse.vo.SalesVo;
import com.mind.mind_warehouse.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SaleManageService {

    @Autowired
    SalesManagementMapper smm;

    @Autowired
    ProductStockMapper psm;

    @Autowired
    FinanceAccountsReceivePayMapper farpm;

    @Autowired
    WarehouseInMapper wim;

    public PageInfo<SalesVo> queryOnPageByConditon(Integer now, Integer size, String orderNum,
                                                   String pcode, String pname, Integer status) {
        PageHelper.startPage(now, size);
        List<SalesVo> list = smm.selectAllSalesVo(orderNum, pcode, pname, status);
        return new PageInfo<>(list);
    }

    public PageInfo<SalesVo> queryReturnOnPageByConditon(Integer now, Integer size, String returnNum,
                                                   String pcode, String pname) {
        PageHelper.startPage(now, size);
        List<SalesVo> list = smm.selectAllReturnSalesVo(returnNum, pcode, pname);
        return new PageInfo<>(list);
    }

    @Transactional
    public int addData(SalesManagement salesManagement) {
        //判断是否有产品
        Integer productId = salesManagement.getProductId();
        if (productId == null) {
            return 0;
        }
        //查看库存信息
        ProductStock productStock = psm.selectByProductId(productId);
        if (productStock == null) {
            return 0;
        }

        //更新库存-减少库存
        int newStock = productStock.getStock() - salesManagement.getQuantity();

        if (newStock < 0) {
            return 0;
        }

        //增加锁定库存
        int newLockStock = productStock.getLockStock() + salesManagement.getQuantity();

        productStock.setStock(newStock);
        productStock.setLockStock(newLockStock);
        productStock.setUpdateTime(new Date());

        //更新库存表
        int update = psm.update(productStock);

        int insert = smm.insert(salesManagement);

        return insert + update;
    }

    @Transactional
    public int updateData(SalesManagement salesManagement, Integer oldQuantity) {
        //判断是否有产品
        Integer productId = salesManagement.getProductId();
        if (productId == null) {
            System.out.println(1);
            return 0;
        }
        //查看库存信息
        ProductStock productStock = psm.selectByProductId(productId);
        if (productStock == null) {
            System.out.println(2);
            return 0;
        }

        //更新库存- 变动值应该为新旧数量的差值
        int newStock = productStock.getStock() - (salesManagement.getQuantity() - oldQuantity);

        if (newStock < 0) {
            System.out.println(3);
            return 0;
        }

        //更新锁定库存
        int newLockStock = productStock.getLockStock() + (salesManagement.getQuantity() - oldQuantity);

        productStock.setStock(newStock);
        productStock.setLockStock(newLockStock);
        productStock.setUpdateTime(new Date());

        //更新库存表
        int update = psm.update(productStock);

        int res = smm.updateByPrimaryKey(salesManagement);

        return res + update;
    }

    public SalesManagement getSalesInfoById(Integer id) {
        return smm.selectByPrimaryKey(id);
    }

    public int updateSale(SalesManagement salesManagement) {
        return smm.updateByPrimaryKey(salesManagement);
    }

    @Transactional
    public int addTradeNo(Integer id, String tradNo) {
        Integer financeId = farpm.selectIdByTradeNo(tradNo);

        if (financeId == null || financeId < 1) {
            return -1;//流水号不存在
        }

        return smm.addTradeNo(id, tradNo, new Date());
    }

    @Transactional
    public int addReturnIn(Integer id, String returnIn) {

        Integer inId = wim.selectIdByOrder(returnIn);
        if (inId == null || inId < 1) {
            return -1;//入库单号不存在
        }

        return smm.addReturnInNum(id, returnIn, new Date());
    }

    public List<SalesVo> queryByIds(int[] ids) {
        return smm.selectSalesVoByIds(ids);
    }
}
