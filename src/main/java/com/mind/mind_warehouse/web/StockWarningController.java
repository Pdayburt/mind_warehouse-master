package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.StockWarningService;
import com.mind.mind_warehouse.vo.StockListVo;
import com.mind.mind_warehouse.vo.StockWarningVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("StockWarn")
@Api(value ="库存预警")
public class StockWarningController {

    @Autowired
    StockWarningService stockWarningService;
    @GetMapping("/list")
    @ApiOperation(value = "库存预警")
    public ResultResponse<PageInfo<StockWarningVo>> StockWarn(@RequestParam(value = "now",defaultValue = "1") Integer now,
                                                              @RequestParam(value="size",defaultValue = "2") Integer size, String prCode, String prName,
                                                              Integer id){
        ResultResponse<PageInfo<StockWarningVo>> result=null;
        try{
            PageInfo<StockWarningVo> list=stockWarningService.researchStockWarnByPages(now,size,prCode,prName,id);
            result=new ResultResponse<>(200,"查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询失败");
        }
        return result;
    }
}