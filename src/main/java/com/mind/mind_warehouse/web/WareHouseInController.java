package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;

import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WareHouseInService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.WareHouseInVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author hnarry
 * @date 2022/6/15 14:14
 */
@RestController
@RequestMapping("in")
@Api(value ="入库管理")
public class WareHouseInController {

    @Autowired
    WareHouseInService wareHouseInService;
    @GetMapping("/list")
    @ApiOperation(value = "查询入库")
    public ResultResponse<PageInfo<WareHouseInVo>> logistics(@RequestParam(value = "now",defaultValue = "1") Integer now,
                                                             @RequestParam(value="size",defaultValue = "2") Integer size, String orderNum, Integer type, Integer status){
        ResultResponse<PageInfo<WareHouseInVo>> result=null;
        try{
            PageInfo<WareHouseInVo> list=wareHouseInService.researchWareHouseInByPages(now,size,orderNum,type,status);
            result=new ResultResponse<>(200,"查询成功",list);
        }catch (Exception e){
            e.printStackTrace();;
            result=new ResultResponse<>(201,"加失败");
        }
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增入库")
    public ResultResponse<Void> add(@RequestBody WareHouseInVo wareHouseInVo){
        ResultResponse<Void> result = null;
        try {
//            String inCode= CodeCreateUtil.getCode(CodeDirectory.WAREHOUSE_IN_CODE);
//            System.out.println(inCode);
//            wareHouseInVo.setInOutId(inCode);
//            wareHouseInVo.setCreatTime(new Date());
//            wareHouseInVo.setUpdateTime(new Date());
            wareHouseInService.add(wareHouseInVo);
            result = new ResultResponse<>(200,"新增入库成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"新增入库失败");
        }
        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改入库")
    public ResultResponse<Void> modify(@RequestBody WareHouseInVo wareHouseInVo){
        ResultResponse<Void> result = null;
        try {
//            获取id查询warehousein对象
            WareHouseInVo warein = wareHouseInService.researchWareHouseInId(wareHouseInVo.getId());
            warein.setType(wareHouseInVo.getType());
            warein.setOrderNum(wareHouseInVo.getOrderNum());
            warein.setSupplierId(wareHouseInVo.getSupplierId());
           warein.setPcode(wareHouseInVo.getPcode());
           warein.setPname(wareHouseInVo.getPname());
            warein.setUpdateTime(wareHouseInVo.getUpdateTime());
            wareHouseInService.modifyWarehouseIn(wareHouseInVo);
            result = new ResultResponse<>(200,"修改入库成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"修改入库失败");
        }
        return result;
    }

    @DeleteMapping("/del{id}")
    @ApiOperation(value = "删除入库")
    public ResultResponse<Void> del(@PathVariable("id") int id){

        ResultResponse<Void> result = null;
        try {
            int deleteItem =wareHouseInService.removeWareIn(id);
            if (deleteItem == 1) {
                result = new ResultResponse<>(200, "删除入库成功");
            } else {
                result = new ResultResponse<>(201, "删除入库失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除入库失败");
        }
        return result;
    }

    @DeleteMapping("/delBatch")
    @ApiOperation(value="批量删除")
    public ResultResponse<Void> delBatch ( @RequestParam("ids") int[] ids){
        ResultResponse<Void> result = null;
        try {
            int nums = wareHouseInService.removeByKeys(ids);
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

}
