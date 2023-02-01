package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Customer;
import com.mind.mind_warehouse.mapper.CustomerMapper;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    public int add(Customer customer){
        if (customer.getCode() == ""){
            customer.setCode(CodeCreateUtil.getCode(CodeDirectory.CUSTOMER_CODE));
        }
        if (customer.getCode() == null){
            customer.setCode(CodeCreateUtil.getCode(CodeDirectory.CUSTOMER_CODE));
        }
        return customerMapper.insert(customer);
    }

    public int remove(Integer id){
        return customerMapper.deleteById(id);
    }

    public int modify(Customer customer){
        if (customer.getCode().toString() == ""){
            customer.setCode(CodeCreateUtil.getCode(CodeDirectory.CUSTOMER_CODE));
        }
        return customerMapper.updateByPrimaryKey(customer);
    }

    public Customer findById(Integer id){
        return customerMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Customer> findByLike(Integer pageNum,Integer pageSize,String code,String name,String tel){
        PageHelper.startPage(pageNum,pageSize);
        List<Customer> list = customerMapper.selectByLike(code, name, tel);
        return new PageInfo<>(list);
    }

    public List<Customer> findAll(){
        return customerMapper.selectAll();
    }

    public List<Customer> findAllByIds(Integer[] ids){
        return customerMapper.selectByIds(ids);
    }
}
