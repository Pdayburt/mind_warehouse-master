package com.mind.mind_warehouse;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.entity.ProductType;
import com.mind.mind_warehouse.mapper.ProductMapper;
import com.mind.mind_warehouse.mapper.WarehouseDamageMapper;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.ProductService;
import com.mind.mind_warehouse.service.ProductTypeService;
import com.mind.mind_warehouse.web.ProductTypeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MindWarehouseApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ProductTypeService productTypeService;
    @Test
    void test(){
        PageInfo<ProductType> productTypePageInfo = productTypeService.queryProductByTypeIdAndName(1, 3, null, null);
        List<ProductType> list = productTypePageInfo.getList();
        for (ProductType p:list){
            System.out.println(p);
        }
    }
    @Autowired
    ProductTypeController productTypeController;
    @Test
    void test01(){
        ResultResponse<PageInfo<ProductType>> pageInfoResultResponse = productTypeController.queryProductType(1, 3, null, null);
        List<ProductType> list = pageInfoResultResponse.getData().getList();
        for (ProductType p:list){
            System.out.println(p);
        }
    }

    @Autowired
    ProductService productService;
    @Test
    void test001(){
        Product product = new Product();
        product.setCode("1129");
        product.setName("ttttttt");
        product.setFirmCode("1112");
        product.setInnerCode("131");
        product.setUpdateTime(new Date());
        int add = productService.add(product);
        System.out.println(add);

    }


    @Autowired
    WarehouseDamageMapper warehouseDamageMapper;

    @Test
    void test6(){
        int i = warehouseDamageMapper.selectStockId(4);
        System.out.println(i);
    }
    @Autowired
    ProductMapper productMapper;
    @Test
    void test7(){
        Product product = productService.queryProductById(2);
        System.out.println(product);
    }

}
