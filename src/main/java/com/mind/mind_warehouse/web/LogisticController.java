package com.mind.mind_warehouse.web;


import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.entity.Logistics;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.LogisticsService;
import com.mysql.cj.log.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author hnarry
 * @date 2022/6/14 15:38
 */

@RestController
@RequestMapping("logistics")
@Api(value ="承运商")
public class LogisticController {


    @Autowired
    LogisticsService logisticsService;
    @GetMapping("/list")
    @ApiOperation(value = "查询承运商")
    public ResultResponse<PageInfo<Logistics>> logistics(@RequestParam(value = "now",defaultValue = "1") Integer now,
                                               @RequestParam(value="size",defaultValue = "2") Integer size,String code,Integer id){
        ResultResponse<PageInfo<Logistics>> result=null;
        try{
            PageInfo<Logistics> list=logisticsService.researchLogByPages(now,size,code,id);
            result=new ResultResponse<>(200,"查询承运商成功",list);
        }catch (Exception e){
            e.printStackTrace();;
            result=new ResultResponse<>(201,"加载承运商失败");
        }
        return result;
    }



    @PostMapping("/add")
    @ApiOperation(value = "新增承运商")
    public ResultResponse<Void> add(@RequestBody Logistics logistics) throws IOException {
        ResultResponse<Void> result =null;
        try{
            int add = logisticsService.add(logistics);
            if (add == 1) {
                result = new ResultResponse<>(200, "添加成功");
            }else{
                result=new ResultResponse<>(201,"添加失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result=new ResultResponse<>(201,"添加失败");
        }
        return  result;

    }

    @PutMapping("/modify")
    @ApiOperation(value="修改承运商")
    public ResultResponse<Void> modify (@RequestBody Logistics logistics){
        ResultResponse<Void> result = null;
        try {
            int num = logisticsService.modify(logistics);
            if (num == 1) {
                result = new ResultResponse<>(200, "修改成功");
            } else {
                result = new ResultResponse<>(201, "修改失败");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "修改失败");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value="删除承运商")
    public ResultResponse<Void> del ( @PathVariable("id") int id){
        ResultResponse<Void> result = null;
        try {
            int delete = logisticsService.removeByKey(id);
            if (delete == 1) {
                result = new ResultResponse<>(200, "删除成功");
            } else {
                result = new ResultResponse<>(201, "删除失败");
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除失败");
        }
        return result;
    }

    @DeleteMapping("/delBatch")
    @ApiOperation(value="批量删除承运商")
    public ResultResponse<Void> delBatch ( @RequestParam("ids") int[] ids){
        ResultResponse<Void> result = null;
        try {
            int nums = logisticsService.removeByKeys(ids);
            if (nums >= 1) {
                result = new ResultResponse<>(200, "批量删除成功");

            } else {
                result = new ResultResponse<>(201, "批量删除失败");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "批量删除失败");
        }
        return result;
    }

    @GetMapping("/llist")
    public ResultResponse<List<Logistics>> tlist(){

        ResultResponse<List<Logistics>> result = null;
        try {
            List<Logistics> lists = logisticsService.researchAll();
            result = new ResultResponse<>(200,"查询成功!",lists);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败!");
        }
        return result ;
    }

}
