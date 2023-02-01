package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.StockListService;
import com.mind.mind_warehouse.vo.StockListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("StockResearch")
@Api(value ="库存查询")
public class StockListController {

    @Autowired
    StockListService stockListService;

    @GetMapping("/list")
    @ApiOperation(value = "查询库存")
    public ResultResponse<PageInfo<StockListVo>> warehouseOut(@RequestParam(value = "now",defaultValue = "1") Integer now,
                                                              @RequestParam(value="size",defaultValue = "2") Integer size, String pduCode, String pduName,
                                                              Integer id){
        ResultResponse<PageInfo<StockListVo>> result=null;
        try{
            PageInfo<StockListVo> list=stockListService.researchStockListByPages(now,size,pduCode,pduName,id);
            result=new ResultResponse<>(200,"查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询失败");
        }
        return result;
    }
}
