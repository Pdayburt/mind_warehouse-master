package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Supplier;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public ResultResponse<Void> add(@RequestBody Supplier supplier){
        ResultResponse<Void> result = null;
        try {
            supplier.setCreatTime(new Date());
            supplier.setUpdateTime(new Date());
            supplier.setIsDelete(0);
            int i = supplierService.add(supplier);
            if (i > 0){
                result = new ResultResponse<>(200,"添加成功");
            }else {
                result = new ResultResponse<>(201,"添加失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加失败");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    public ResultResponse<Void> del(@PathVariable Integer id){
        ResultResponse<Void> result = null;
        try {
            int i = supplierService.remove(id);
            if (i == 1){
                result = new ResultResponse<>(200,"删除成功");
            }else {
                result = new ResultResponse<>(201,"删除失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<Void> modify(@RequestBody Supplier supplier){
        ResultResponse<Void> result = null;
        try {
            Supplier sup = supplierService.findById(supplier.getId());
            supplier.setCreatTime(sup.getCreatTime());
            supplier.setUpdateTime(new Date());
            int i = supplierService.modify(supplier);
            if (i == 1){
                result = new ResultResponse<>(200,"修改成功");
            }else {
                result = new ResultResponse<>(201,"修改失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"修改失败");
        }
        return result;
    }

    @GetMapping("/like")
    public ResultResponse<PageInfo<Supplier>> showLike(@RequestParam(defaultValue = "1") Integer pageNum,
                                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                                        @RequestParam(defaultValue = "") String code,
                                                       @RequestParam(defaultValue = "") String name,
                                                       @RequestParam(defaultValue = "") Integer type,
                                                       @RequestParam(defaultValue = "") String tel){
        ResultResponse<PageInfo<Supplier>> result = null;
        try {
            PageInfo<Supplier> pageInfo = supplierService.findByLike(pageNum,pageSize,code,name,type,tel);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

    @GetMapping("/queryAllSupplier")
    public ResultResponse<List<Supplier>> queryAllSupplier(){
        ResultResponse<List<Supplier>> result = null;
        try {
            List<Supplier> suppliers = supplierService.queryAll();
            result=new ResultResponse<>(200,"查询所有供应商成功",suppliers);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(200,"查询所有供应商失败");
        }
        return result;
    }

}
