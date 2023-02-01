package com.mind.mind_warehouse.web;

import com.mind.mind_warehouse.entity.AuditLog;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.AuditService;
import com.mind.mind_warehouse.util.CodeCreateUtil;
import com.mind.mind_warehouse.util.CodeDirectory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("audit")
@Api(value = "审核接口")
public class AuditController {
    //errorType 0正常完成 1订单不存在 2订单已删除，无法审核 3已审核成功订单，无需再审核 -1未知

    @Autowired
    AuditService as;

    @PostMapping("/operation")
    @ApiOperation(value = "审核提交接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="empId",value = "审核员工id",dataType = "int"),
            @ApiImplicitParam(name="orderNum",value = "需要审核的订单号",dataType = "String"),
            @ApiImplicitParam(name="orderType",value = "订单的类型",dataType = "int"),
            @ApiImplicitParam(name="opType",value = "审核操作，0不通过 1通过",dataType = "int"),
            @ApiImplicitParam(name="reason",value = "审核意见",dataType = "String")
    })
    public ResultResponse<Void> auit(@RequestParam("empId") Integer auditId,
                                     @RequestParam("orderNum") String orderNum,
                                     @RequestParam("orderType") Integer orderType,
                                     @RequestParam("opType") Integer opType,
                                     @RequestParam("reason") String reason) {
        ResultResponse<Void> result;
        int errType = 0;
        //根据订单类型查询，查询订单信息
        //有订单
        //is_delete=1 审核失败
        //status为已审核 转失败记录

        //无订单信息
        try {
            AuditLog auditLog = new AuditLog(orderNum, orderType, auditId, opType, reason);
            auditLog.setErrorType(errType);
            auditLog.setOpTime(new Date());
            as.addLog(auditLog);
            result = new ResultResponse<>(200, "添加审核成功!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201, "添加审核失败!");
        }

        return result;
    }
}
