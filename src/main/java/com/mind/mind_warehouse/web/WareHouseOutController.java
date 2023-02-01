package com.mind.mind_warehouse.web;


import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.WareHouseOutService;


import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import com.mind.mind_warehouse.vo.WarehouseOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("out")
@Api(value ="出库管理")
public class WareHouseOutController {

    @Autowired
    WareHouseOutService wareHouseOutService;


    @GetMapping("/list")
    @ApiOperation(value = "查询出库")
    public ResultResponse<PageInfo<WarehouseOutVo>> warehouseOut(@RequestParam(value = "now",defaultValue = "1") Integer now,
                                                                 @RequestParam(value="size",defaultValue = "2") Integer size, String orderNum,String cTel){
        ResultResponse<PageInfo<WarehouseOutVo>> result=null;
        try{
            PageInfo<WarehouseOutVo> list=wareHouseOutService.researchWareHouseOutByPages(now,size,orderNum,cTel);
            result=new ResultResponse<>(200,"查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增出库")
    public ResultResponse<Void> add(@RequestBody WarehouseOutVo warehouseOutVo){
        ResultResponse<Void> result = null;
        try {
            int w =wareHouseOutService.add(warehouseOutVo);
            if (w> 0){
                result = new ResultResponse<>(200,"新增出库成功");
            }else {
                result = new ResultResponse<>(201,"新增出库失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"新增出库失败");
        }
        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改出库")
    public ResultResponse<Void> modify(@RequestBody WarehouseOutVo warehouseOutVo){
        ResultResponse<Void> result = null;
        try {
//            获取id查询出库对象
            WarehouseOutVo wareOut =wareHouseOutService.researchWarehouseOutId(warehouseOutVo.getId());
            wareOut.setType(warehouseOutVo.getType());
            wareOut.setOrderNum(warehouseOutVo.getOrderNum());
            wareOut.setCustomerCode(warehouseOutVo.getCustomerCode());
            wareOut.setProductId(warehouseOutVo.getProductId());
            wareOut.setChangeNum(warehouseOutVo.getChangeNum());
            wareOut.setChangeId(warehouseOutVo.getChangeId());
            wareOut.setFormEmpId(warehouseOutVo.getFormEmpId());
            wareOut.setAuditEmpId(warehouseOutVo.getAuditEmpId());
            wareOut.setAuditTime(warehouseOutVo.getAuditTime());
            wareOut.setStatus(warehouseOutVo.getStatus());

            wareHouseOutService.updateWareHouseOut(wareOut);
            result = new ResultResponse<>(200,"修改出库成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"修改出库失败");
        }
        return result;
    }

    @DeleteMapping("/del{id}")
    @ApiOperation(value = "删除出库")
    public ResultResponse<Void> del( @PathVariable("id") int id){

        ResultResponse<Void> result = null;
        try {
            int deleteItem =wareHouseOutService.removeWareOut(id);
            if (deleteItem == 1) {
                result = new ResultResponse<>(200, "删除出库成功");
            } else {
                result = new ResultResponse<>(201, "删除出库失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除出库失败");
        }
        return result;
    }


    @DeleteMapping("/delBatch")
    @ApiOperation(value="批量删除")
    public ResultResponse<Void> delBatch ( @RequestParam("ids") int[] ids){
        ResultResponse<Void> result = null;
        try {
            int nums = wareHouseOutService.removeByKeys(ids);
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

    @GetMapping("/queryByKey")
    @ApiOperation(value = "查询全部")
    public ResultResponse<Void> queryAll(Integer id){

        ResultResponse<Void> result = null;
        try {
            WarehouseOutVo wro =wareHouseOutService.queryAll(id);
            result=new ResultResponse<>(200,"查询成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }


    @GetMapping("/queryDelivery")
    @ApiOperation(value = "查询已发运次数")
    public ResultResponse<WarehouseMoveVo> queryDelivery(){
        ResultResponse<WarehouseMoveVo> result = null;
        try {
            WarehouseMoveVo delivery = wareHouseOutService.queryDelivery();
            result = new ResultResponse<>(200,"查询已发运次数成功",delivery);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询已发运次数失败,等下再来试试");
        }
        return result;
    }

    @GetMapping("/queryReadyForDelivery")
    @ApiOperation(value = "查询待发运次数")
    public ResultResponse<WarehouseMoveVo> queryReadyForDelivery(){
        ResultResponse<WarehouseMoveVo> result = null;
        try {
            WarehouseMoveVo readyForDelivery = wareHouseOutService.queryReadyForDelivery();
            result = new ResultResponse<>(200,"查询待发运次数成功",readyForDelivery);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询待发运次数失败,等下再来试试");
        }
        return result;
    }


}
