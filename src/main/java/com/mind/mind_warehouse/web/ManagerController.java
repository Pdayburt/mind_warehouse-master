package com.mind.mind_warehouse.web;

import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.EmployeeService;
import com.mind.mind_warehouse.service.LogService;
import com.mind.mind_warehouse.vo.LogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

/**
 * @author hnarry
 * @date 2022/6/14 14:22
 */

@RestController
@RequestMapping("/employee")
@Api(value ="用户登录")
public class ManagerController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    LogService logService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")

    public ResultResponse<Employee> login(@ApiParam(name="account",value = "接收用户名和密码参数") @RequestBody Employee employee){
        ResultResponse<Employee> result = null;
        Employee a=employeeService.login(employee.getAccount());
        //记录登录日志
        logService.addLog(employee);
        if (a == null){
            result =new ResultResponse<>(201,"账号不存在");

        }else if (a.getPassword().equals(employee.getPassword())){
            result=new ResultResponse<>(200,"登录成功",a);
        }else{
            result=new ResultResponse<>(202,"密码错误");
        }
        return result;
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();//清空@SessionAttributes注解的信息
        return "redirect:/jsp/login.jsp";
    }
}
