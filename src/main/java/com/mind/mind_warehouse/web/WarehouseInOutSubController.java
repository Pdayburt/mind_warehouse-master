package com.mind.mind_warehouse.web;


import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WarehouseInOutSubService;
import com.mind.mind_warehouse.vo.QuarterVo;
import com.mind.mind_warehouse.vo.WarehouseInOutSubVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inOutSub")
@Api(value = "对产品出入库的相关操作")
public class WarehouseInOutSubController {


    @Autowired
    WarehouseInOutSubService warehouseInOutSubService;



    @GetMapping("/queryOutNum")
    @ApiOperation(value = "查询出库产品总数")
    public ResultResponse<WarehouseInOutSubVo> queryOutNum(){
        ResultResponse<WarehouseInOutSubVo> result = null;

        try {
            WarehouseInOutSubVo outNum = warehouseInOutSubService.queryOutNum();
            result = new ResultResponse<>(200,"查询出库产品总数成功",outNum);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询出库产品总数失败,等下再来试试");
        }
        return result;
    }


    @GetMapping("/list")
    public ResultResponse<PageInfo<QuarterVo>> findList(@RequestParam(defaultValue = "1") Integer pageNum,
                                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                                        String productCode, String productName, String warehouseName ){
        ResultResponse<PageInfo<QuarterVo>> result = null;
        try {
            PageInfo<QuarterVo> pageInfo = warehouseInOutSubService.findQuarterIn(pageNum, pageSize,productCode,productName,warehouseName);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }


}
