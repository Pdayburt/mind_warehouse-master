package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.ProductStock;
import com.mind.mind_warehouse.vo.ProductStockVo;
import com.mind.mind_warehouse.vo.SalesVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ProductStockMapper {

    int updateProductStockById(@Param("id") int id, @Param("warehouseId") int warehouseId, @Param("storageId") int storageId, @Param("updateTime")Date updateTime);

    int updateStorageIdByPid(@Param("pid") int pid, @Param("newStorageId") int newStorageId, @Param("updateTime")Date updateTime);


    int updateProductStock(@Param("psid") Integer psid, @Param("stock") Integer stock);

    int update(ProductStock productStock);


    @Select("select stock from mw_product_stock where id = #{id}")
    int selectStockById(Integer id);

    ProductStock selectByPrimaryKey(Integer id);
    ProductStock selectByProductId(Integer productId);

}
