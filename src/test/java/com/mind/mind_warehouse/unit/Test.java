package com.mind.mind_warehouse.unit;

import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.mapper.SalesManagementMapper;
import com.mind.mind_warehouse.mapper.WarehouseMapper;
import com.mind.mind_warehouse.service.WarehouseInOutSubService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.SalesVo;
import com.mind.mind_warehouse.vo.WarehouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test {

    @org.junit.jupiter.api.Test
    public void test1() {
        System.out.println(CodeCreateUtil.getCode(CodeDirectory.PRODUCT_CODE));
        System.out.println(CodeCreateUtil.getTradeNo());
    }

    @Autowired
    WarehouseMapper wm;

    @org.junit.jupiter.api.Test
    public void test2() {
        List<WarehouseVo> list = wm.selectAllWarehouseAndStroage();
        System.out.println(list);
    }

    @Autowired
    SalesManagementMapper smm;

    @org.junit.jupiter.api.Test
    public void test3() {
        List<SalesVo> list = smm.selectAllSalesVo(null,null,null,null);
        System.out.println(list.size());
        for (SalesVo salesVo : list) {
            System.out.println(salesVo);
        }
    }


}
