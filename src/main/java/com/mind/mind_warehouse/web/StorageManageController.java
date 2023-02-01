package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Logistics;
import com.mind.mind_warehouse.entity.Storage;
import com.mind.mind_warehouse.entity.StorageType;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.StorageManageService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.StorageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("storageManage")
@Api(value = "基本库位管理相关接口")
public class StorageManageController {

    @Autowired
    StorageManageService sms;

    @PostMapping("/add")
    @ApiOperation(value = "新增库位")
    public ResultResponse<Void> addStorage(@RequestBody Storage storage) {
        ResultResponse<Void> result;

        try {
            String stCode = CodeCreateUtil.getCode(CodeDirectory.STORAGE_CODE);
            System.out.println(stCode);
            storage.setCode(stCode);
            storage.setUpdateTime(new Date());
            storage.setCreateTime(new Date());
            storage.setIsDelete(0);
            sms.add(storage);
            result = new ResultResponse<>(200, "添加库位成功!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加库位失败!");
        }

        return result;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改库位")
    public ResultResponse<Void> modify(@RequestBody Storage storage) {
        ResultResponse<Void> result;
        try {
            //根据 ID 查询出来 当前数据  把前台传递过来的数据  赋值到新查询出来的对象上
            Storage sto = sms.queryById(storage.getId());
            if (sto == null) {
                result = new ResultResponse<>(201, "未查找到该库位信息！");
            } else {
                sto.setWarehouseId(storage.getWarehouseId());
                sto.setName(storage.getName());
                sto.setTypeId(storage.getTypeId());
                sto.setUpdateTime(new Date());
                sto.setIsBan(storage.getIsBan());
                sto.setIsDefault(storage.getIsDefault());
                sms.modify(sto);
                result = new ResultResponse<>(200, "修改库位成功!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "修改库位失败!");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    public ResultResponse<Void> del(@PathVariable("id") Integer id) {
        ResultResponse<Void> result;

        try {
            Storage sto = sms.queryById(id);
            if (sto == null) {
                result = new ResultResponse<>(201, "未查询到库位信息!");
            } else {
                sto.setIsDelete(1);
                sto.setUpdateTime(new Date());
                sms.modify(sto);
                result = new ResultResponse<>(200, "删除库位成功!");
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "删除库位失败!");
        }
        return result;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询库位列表")
    public ResultResponse<PageInfo<StorageVo>> querylist(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer now,
            @RequestParam(value = "pageSize", defaultValue = "8") Integer size,
            String code,
            String name,
            @RequestParam(value = "warehouseId", defaultValue = "0") Integer warehouseId) {
        //integer如果传入null 会报空指针错误，但是string却不会
        ResultResponse<PageInfo<StorageVo>> result;

        try {
            PageInfo<StorageVo> pageInfo = sms.queryOnPageByCondition(now, size, code, name, warehouseId);
            result = new ResultResponse<>(200, "查询库位列表成功!", pageInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询库位列表失败!");
        }
        return result;
    }
    @GetMapping("/typeList")
    @ApiOperation(value = "查询库位类型列表")
    public ResultResponse<List<StorageType>> typeList() {
        ResultResponse<List<StorageType>> result = null;

        try {
            List<StorageType> list = sms.queryAllType();
            result = new ResultResponse<>(200, "查询库位列表成功!", list);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "查询库位列表失败!");
        }
        return result;
    }

    @GetMapping("/querylist")
    public ResultResponse<List<Storage>> tlist(){

        ResultResponse<List<Storage>> result = null;
        try {
            List<Storage> lists = sms.queryAll();
            result = new ResultResponse<>(200,"查询成功!",lists);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败!");
        }
        return result ;
    }
}
