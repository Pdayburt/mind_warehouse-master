package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.ProductType;
import com.mind.mind_warehouse.mapper.ProductTypeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeMapper productTypeMapper;
//    @CacheEvict(value = "ProductType",key = "#root.args[0].id")
    public int add(ProductType productType){
        return productTypeMapper.insert(productType);
    }
//    @CacheEvict(value = "ProductType",key = "#id")
    public int remove(Integer id){
        return productTypeMapper.deleteByPrimaryKey(id);
    }
//    @CacheEvict(value = "ProductType",key = "#root.args[0].id")
    public int modify(ProductType productType){
        return productTypeMapper.updateByPrimaryKey(productType);
    }
//    @Cacheable(value = "ProductType",key = "#root.methodName+#root.args[0]+#root.args[1]+#root.args[2]+#root.args[3]")
    public PageInfo<ProductType> queryProductByTypeIdAndName(Integer pageNum,Integer pageSize,
                                                             Integer code ,String name){
        PageHelper.startPage(pageNum, pageSize);
        List<ProductType> productTypes = productTypeMapper.selectByIDAndName(code, name);
        return new PageInfo<>(productTypes);

    }
//    List<ProductType> selectAllFatherProductType()

    public List<ProductType> queryAllFatherProductType(){
       return productTypeMapper.selectAllFatherProductType();
    }
    public List<ProductType> queryNotFatherProductType(){
        return productTypeMapper.selectNotFatherProductType();
    }

//    List<ProductType> selectByIds(@Param("ids") int[] ids);
    public List<ProductType> queryByIds(int[] ids){
        return productTypeMapper.selectByIds(ids);
    }


}
