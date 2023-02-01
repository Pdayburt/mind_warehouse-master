package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.entity.WarehouseInventory;
import com.mind.mind_warehouse.mapper.EmployeeMapper;
import com.mind.mind_warehouse.mapper.WarehouseInventoryMapper;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.InventoryPlusVo;
import com.mind.mind_warehouse.vo.WarehouseInventoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WarehouseInventoryService {

    @Autowired
    WarehouseInventoryMapper inventoryMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Transactional
   public int add(WarehouseInventoryVo inventoryVo){
       //1.先添加盘点单
        WarehouseInventory inventory = new WarehouseInventory();
        //设置盘点单
        if (inventoryVo.getInventoryNum() != null){
            inventory.setInventoryNum(inventoryVo.getInventoryNum());
        }else {
            String code = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_INVENTORY);
            inventory.setInventoryNum(code);
        }
        //设置批次
        String batch = CodeCreateUtil.getBatch();
        inventory.setBatch(batch);
        inventory.setStatus(0);
        //1.1 通过前台接收的名称查询员工的id
        Employee employee = employeeMapper.selectNameAndPwd(inventoryVo.getEmpName());
        if (employee != null){
            inventory.setCreateByEmp(employee.getName());
        }else {
            return 0;
        }
        inventory.setCreateTime(new Date());
        inventory.setIsDelete(0);
        inventoryMapper.insert(inventory);
        //2.在把盘点单号和产品id相关联，以及添加对应的盘点数量
        List<Map> mapList = new ArrayList<>();
        for (int i=0;i<inventoryVo.getCheckedIds().size();i++){
            HashMap map = new HashMap<>();
            map.put("productId",inventoryVo.getCheckedIds().get(i));
            map.put("account",inventoryVo.getInventoryCount().get(i));
            mapList.add(map);
        }
        return inventoryMapper.insertInventoryAndProductAndCount(inventory.getId(),mapList);

    }

    @Transactional
    public int remove(Integer id){
        //1.先删除中间表对应的关系
        inventoryMapper.deleteInventoryAndProducts(id);
        //2.在删除盘点表
        return inventoryMapper.deleteById(id);
    }

    @Transactional
    public int modify(WarehouseInventoryVo inventoryVo){
        //1.先删除中间表对应的关系
        inventoryMapper.deleteInventoryAndProducts(inventoryVo.getId());
        //2.在把盘点单号和产品id相关联,以及添加对应的盘点数量
        List<Map> mapList = new ArrayList<>();
        for (int i=0;i<inventoryVo.getCheckedIds().size();i++){
            HashMap map = new HashMap<>();
            map.put("productId",inventoryVo.getCheckedIds().get(i));
            map.put("account",inventoryVo.getInventoryCount().get(i));
            mapList.add(map);
        }
        inventoryMapper.insertInventoryAndProductAndCount(inventoryVo.getId(),mapList);
        //3.在修改盘点单
        WarehouseInventory inventory = inventoryMapper.selectByPrimaryKey(inventoryVo.getId());
        if (inventoryVo.getInventoryNum() != null){
            inventory.setInventoryNum(inventoryVo.getInventoryNum());
        }else {
            String code = CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_INVENTORY);
            inventory.setInventoryNum(code);
        }
        //3.1 通过前台接收的名称查询员工的id
        Employee employee = employeeMapper.selectNameAndPwd(inventoryVo.getEmpName());
        if (employee != null){
            inventory.setCreateByEmp(employee.getName());
        }else {
            return 0;
        }
        inventory.setUpdateTime(new Date());
        return inventoryMapper.updateByPrimaryKey(inventory);
    }

    public PageInfo<WarehouseInventory> findByLike(Integer pageNum,Integer pageSize,String inventoryNum,Integer status){
        PageHelper.startPage(pageNum,pageSize);
        List<WarehouseInventory> list = inventoryMapper.selectByLike(inventoryNum, status);
        return new PageInfo<>(list);
    }

    public PageInfo<InventoryPlusVo> findInventoryPlusVoById(Integer pageNum,Integer pageSize,Integer id){
        PageHelper.startPage(pageNum,pageSize);
        List<InventoryPlusVo> voList = inventoryMapper.selectInventoryPlusById(id);
        return new PageInfo<>(voList);
    }

}
