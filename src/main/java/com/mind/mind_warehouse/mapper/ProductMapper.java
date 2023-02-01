package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
    List<ProductVo> selectProductByCons(@Param("code")Integer code, @Param("name")String name,
                                        @Param("type")Integer type, @Param("tel")String tel);
    List<ProductVo> selectByIds(@Param("ids") int[] ids);
}