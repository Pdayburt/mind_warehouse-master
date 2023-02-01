package com.mind.mind_warehouse.web;


import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.FinanceType;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.FinanceTypeService;
import com.mind.mind_warehouse.service.FinancialCategoryService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.FinancialCategoryVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("FinancialCategory")
@Api(value = "对财务类别的相关操作")
public class FinancialCategoryController {

    @Autowired
    FinancialCategoryService financialCategoryService;

    @Autowired
    FinanceTypeService financeTypeService;


    @GetMapping("/queryAllFinanceType")
    @ApiOperation(value = "财务类别查询")
    public ResultResponse<List<FinanceType>> queryAllFinanceType(){
        ResultResponse<List<FinanceType>> result = null;
        try {
            List<FinanceType> typeList = financeTypeService.queryAll();
            result = new ResultResponse<>(200,"查询财务类别成功",typeList);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询财务类别失败,等下再来试试");
        }
        return result;
    }



    @DeleteMapping("/delFinancialCategory{ftid}")
    @ApiOperation(value = "根据单个id删除财务类别信息的操作")
    public ResultResponse<Void> del(@PathVariable("ftid") int ftid){

        ResultResponse<Void> result = null;
        try {
            financialCategoryService.removeFinancialCategoryById(ftid);
            result = new ResultResponse<>(200,"删除财务类别成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除财务类别失败");
        }
        return result;
    }



    @PostMapping("/add")
    @ApiOperation(value = "财务类别的添加操作/封装对象传递")
    public ResultResponse<Void> add(@RequestBody FinancialCategoryVo financialCategoryVo){
        ResultResponse<Void> result = null;
        try {
            String ftcode = CodeCreateUtil.getCode(CodeDirectory.FINANCE_TYPE_CODE);
            financialCategoryVo.setCreateTime(new Date());
            financialCategoryVo.setUpdateTime(new Date());
            financialCategoryVo.setFtcode(ftcode);
            financialCategoryService.addFinancialCategory(financialCategoryVo);
            result = new ResultResponse<>(200,"添加财务类别成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加财务类别失败,请稍后再试");
        }
        return result;
    }


    @PutMapping("/modify")
    @ApiOperation(value = "财务类别的修改操作/封装对象传递")
    public ResultResponse<Void> modify(@RequestBody FinancialCategoryVo financialCategoryVo){
        ResultResponse<Void> result = null;
        try {
            FinancialCategoryVo f = financialCategoryService.queryFinancialCategoryById(financialCategoryVo.getFtid());
            f.setFtname(financialCategoryVo.getFtname());
            f.setFtcode(financialCategoryVo.getFtcode());
            f.setFtnote(financialCategoryVo.getFtnote());
            f.setUpdateTime(new Date());
            financialCategoryService.modifyByFinancialCategoryId(financialCategoryVo);
            result = new ResultResponse<>(200,"更新财务类别成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"更新财务类别失败");
        }
        return result;
    }

    @GetMapping("/download")
    public void downloadExcel(@RequestParam(value = "ids") int[] ids, HttpServletResponse response) throws IOException {

        if (ids.length < 1) {
            return;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String name = "财务类别报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<FinancialCategoryVo> list = financialCategoryService.queryByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), FinancialCategoryVo.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }








}
