package com.mind.mind_warehouse.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.mapper.WarehouseInOutSubMapper;
import com.mind.mind_warehouse.vo.QuarterVo;
import com.mind.mind_warehouse.vo.WarehouseInOutSubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseInOutSubService {

    @Autowired
    WarehouseInOutSubMapper warehouseInOutSubMapper;


    public WarehouseInOutSubVo queryOutNum(){
        return warehouseInOutSubMapper.selectOutNum();
    }

    public PageInfo<QuarterVo> findQuarterIn(Integer pageNum, Integer pageSize, String productCode, String productName, String warehouseName) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuarterVo> listin = warehouseInOutSubMapper.selectQuarterByLikeIn(productCode, productName, warehouseName);
        List<QuarterVo> listout = warehouseInOutSubMapper.selectQuarterByLikeOut(productCode, productName, warehouseName);
        for (QuarterVo vo : listin) {
            for (QuarterVo quarterVo : listout) {
                if (vo.getProductId().equals(quarterVo.getProductId())) {
                    vo.setOutNum(quarterVo.getOutNum());
                }
            }
        }
        return new PageInfo<>(listin);
    }
}
