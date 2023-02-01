package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.mapper.ProductMapper;
import com.mind.mind_warehouse.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;
//    int deleteByPrimaryKey(Integer id);

    @CacheEvict(value = "Product",allEntries = true)
    public int remove(int id){
        return productMapper.deleteByPrimaryKey(id);
    }

//    int insert(Product record);
@CacheEvict(value = "Product",allEntries = true)
    public int add(Product record){
        return productMapper.insert(record);
    }

//    Product selectByPrimaryKey(Integer id);


//    List<Product> selectAll();
//    @Cacheable(value = "Product",key="#root.methodName")
    public List<Product> queryAll(){
        return productMapper.selectAll();
    }

//    int updateByPrimaryKey(Product record);
//@CacheEvict(value = "Product",key = "#root.args[0].id")
    public int modify(Product product){
        return productMapper.updateByPrimaryKey(product);
    }
//    List<Product> selectProductByCons(@Param("code")Integer code, @Param("name")String name,
//                                      @Param("type")Integer type, @Param("tel")String tel);
@Cacheable(value = "Product",key = "#root.methodName+#root.args[0]+#root.args[1]+#root.args[2]+#root.args[3]+#root.args[4]+#root.args[5]")
    public PageInfo<ProductVo> queryProductByCons(Integer pagNum,Integer pageSize,
                                                Integer code,String name,
                                                Integer type,String tel){
        PageHelper.startPage(pagNum, pageSize);
        List<ProductVo> products = productMapper.selectProductByCons(code, name, type, tel);
        return new PageInfo<>(products);
    }
//    @Cacheable(value ="Product",key="#root.methodName+#root.args[0]" )
    public Product queryProductById(Integer id){
        return productMapper.selectByPrimaryKey(id);
    }

//    List<ProductVo> selectByIds(@Param("ids") List<Integer> ids);
//@Cacheable(value ="Product",key="#root.methodName+#root.args[0]" )
    public List<ProductVo> queryByIds(int[] ids){
       return productMapper.selectByIds(ids);
    }



}
