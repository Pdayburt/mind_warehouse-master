package com.mind.mind_warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.mind.mind_warehouse.mapper")
@EnableCaching
public class MindWarehouseApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(MindWarehouseApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
