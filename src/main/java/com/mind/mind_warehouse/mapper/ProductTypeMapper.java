package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.ProductType;
import com.mind.mind_warehouse.vo.ProductTypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductType record);

    ProductType selectByPrimaryKey(Integer id);

    List<ProductType> selectAll();

    int updateByPrimaryKey(ProductType record);
    List<ProductType> selectByIDAndName(@Param("code")Integer code,@Param("name")String name);
    List<ProductTypeVo> selectAllProductTypeVo();
    List<ProductType> selectAllFatherProductType();
    List<ProductType> selectNotFatherProductType();
    List<ProductType> selectByIds(@Param("ids") int[] ids);
}