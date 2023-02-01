package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.FinanceAccountsReceivePay;
import com.mind.mind_warehouse.entity.Permission;
import com.mind.mind_warehouse.mapper.FinanceAccountsReceivePayMapper;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.FinancialBalanceService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import com.mind.mind_warehouse.vo.FinanceAccountsReceivePayVo;
import com.mind.mind_warehouse.vo.ReceivePayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.callback.NameCallback;

@RestController
@RequestMapping("/FinancialBalance")
public class FinancialBalanceController {
    @Autowired
    FinancialBalanceService financialBalanceService;

    @GetMapping("/queryReceiveFinance")
    public ResultResponse<PageInfo<FinanceAccountsReceivePayVo>> queryReceiveFinance(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                                     @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                                     Integer serialNumber, String name, Integer financeTypeId, Integer status, String customerName){
        ResultResponse<PageInfo<FinanceAccountsReceivePayVo>> result=null;
        System.out.println("Controller接受到的status的值为："+status);
        try {
            PageInfo<FinanceAccountsReceivePayVo> financeAccountsReceivePayPageInfo = financialBalanceService.queryByCons(pageNum, pageSize, serialNumber, name, financeTypeId, status, customerName);
            result=new ResultResponse<>(200,"条件查询所有应收帐款成功",financeAccountsReceivePayPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"条件查询所有应收帐款失败");
        }
        return result;
    }
    @GetMapping("/queryPayFinance")
    public ResultResponse<PageInfo<FinanceAccountsReceivePayVo>> queryPayFinance(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                                     @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                                     Integer serialNumber, String name, Integer financeTypeId, Integer status, String supplierName){
        ResultResponse<PageInfo<FinanceAccountsReceivePayVo>> result=null;
        try {
            PageInfo<FinanceAccountsReceivePayVo> financeAccountsReceivePayVoPageInfo = financialBalanceService.queryPayByCons(pageNum, pageSize, serialNumber, name, financeTypeId, status, supplierName);
            result=new ResultResponse<>(200,"条件查询所有应收帐款成功",financeAccountsReceivePayVoPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"条件查询所有应收帐款失败");
        }
        return result;
    }
    @DeleteMapping("/deleteReceiveFinanceById")
    public ResultResponse<Void> deleteReceiveFinanceById(@RequestParam("id")Integer id){
        ResultResponse<Void> result=null;
        try {
            financialBalanceService.removeById(id);
            result=new ResultResponse<>(200,"删除应收帐款成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"删除应收帐款失败");
        }
        return result;
    }
    @PostMapping("/addReceiveFinance")
    public ResultResponse<FinanceAccountsReceivePay> addReceiveFinance(@RequestBody FinanceAccountsReceivePay receivePay){
        ResultResponse<FinanceAccountsReceivePay> result=null;
        String code=null;
        if(receivePay.getType()==1){
            code = CodeCreateUtil.getCode(CodeDirectory.PURCHASE_CODE);

        }else if(receivePay.getType()==2){
            code = CodeCreateUtil.getCode(CodeDirectory.PURCHASE_RETURN_CODE);
        }
        receivePay.setSerialNumber(code);
        try {
             financialBalanceService.add(receivePay);
            result=new ResultResponse<>(200,"增加应收帐款成功",receivePay);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"增加应收帐款失败");
        }
        return result;
    }
    @PutMapping("/updateReceiveFinance")
    public ResultResponse<FinanceAccountsReceivePay> updateReceiveFinance(@RequestBody FinanceAccountsReceivePay receivePay){
        ResultResponse<FinanceAccountsReceivePay> result=null;
        try {
            financialBalanceService.modify(receivePay);
            result=new ResultResponse<>(200,"更新应收帐款成功",receivePay);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"更新应收帐款失败");
        }
        return result;
    }
    @GetMapping("/selectPayByConsBoth")
    public ResultResponse<PageInfo<ReceivePayVo>> selectPayByConsBoth(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                      @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                      Integer serialNumber, String name, Integer financeTypeId){
        ResultResponse<PageInfo<ReceivePayVo>> result=null;
        try {
            PageInfo<ReceivePayVo> receivePayVoPageInfo = financialBalanceService.selectPayByConsBoth(pageNum, pageSize, serialNumber, name, financeTypeId);
            result=new ResultResponse<>(200,"查询成功", receivePayVoPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询失败");
        }
        return result;
    }






}
