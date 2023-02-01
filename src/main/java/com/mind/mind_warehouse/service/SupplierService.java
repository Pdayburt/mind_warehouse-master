package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.mapper.SupplierMapper;
import com.mind.mind_warehouse.entity.Supplier;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierMapper supplierMapper;


    public int add(Supplier supplier){
        if (supplier.getCode() == ""){
            supplier.setCode(CodeCreateUtil.getCode(CodeDirectory.SUPPLIER_CODE));
        }
        if (supplier.getCode() == null){
            supplier.setCode(CodeCreateUtil.getCode(CodeDirectory.SUPPLIER_CODE));
        }
        return supplierMapper.insert(supplier);

    }

    public int remove(Integer id){
        return supplierMapper.deleteById(id);
    }

    public int modify(Supplier supplier){
        if (supplier.getCode().toString() == ""){
            supplier.setCode(CodeCreateUtil.getCode(CodeDirectory.SUPPLIER_CODE));
        }
        return supplierMapper.updateByPrimaryKey(supplier);
    }

    public Supplier findById(Integer id){
        return supplierMapper.selectByPrimaryKey(id);
    }
    public PageInfo<Supplier> findByLike(Integer pageNum,Integer pageSize,String code,String name,Integer type, String tel){
        PageHelper.startPage(pageNum,pageSize);
        List<Supplier> list = supplierMapper.selectByLike(code, name, type, tel);
        return new PageInfo<>(list);
    }

    public List<Supplier> queryAll(){
        return supplierMapper.selectAll();
    }
}
