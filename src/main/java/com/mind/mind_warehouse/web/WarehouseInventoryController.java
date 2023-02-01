package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Customer;
import com.mind.mind_warehouse.entity.WarehouseInventory;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehouseInventoryService;
import com.mind.mind_warehouse.vo.InventoryPlusVo;
import com.mind.mind_warehouse.vo.WarehouseInventoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class WarehouseInventoryController {

    @Autowired
    WarehouseInventoryService inventoryService;

    @PostMapping("/add")
    public ResultResponse<Void> add(@RequestBody WarehouseInventoryVo inventoryVo){
        ResultResponse<Void> result = null;
        try {
            int i = inventoryService.add(inventoryVo);
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
            int i = inventoryService.remove(id);
            if (i > 0){
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
    public ResultResponse<Void> modify(@RequestBody WarehouseInventoryVo inventoryVo){
        ResultResponse<Void> result = null;
        try {
            int i = inventoryService.modify(inventoryVo);
            if (i > 0){
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
    public ResultResponse<PageInfo<WarehouseInventory>> showLike(@RequestParam(defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(defaultValue = "") String inventoryNum,
                                                                 @RequestParam(defaultValue = "") Integer status){
        ResultResponse<PageInfo<WarehouseInventory>> result = null;
        try {
            PageInfo<WarehouseInventory> pageInfo = inventoryService.findByLike(pageNum, pageSize, inventoryNum, status);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

    //盘点单详情
    @GetMapping("/likePlus")
    public ResultResponse<PageInfo<InventoryPlusVo>> showLikePlus(@RequestParam(defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                                                   Integer id){
        ResultResponse<PageInfo<InventoryPlusVo>> result = null;
        try {
            PageInfo<InventoryPlusVo> pageInfo = inventoryService.findInventoryPlusVoById(pageNum, pageSize, id);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

}
