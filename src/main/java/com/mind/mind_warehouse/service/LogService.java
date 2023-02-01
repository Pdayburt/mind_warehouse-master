package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.mapper.EmployeeMapper;
import com.mind.mind_warehouse.mapper.OpLogMapper;
import com.mind.mind_warehouse.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    OpLogMapper logMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    public PageInfo<LogVo> findAll(Integer pageNum,Integer pageSize,LogVo logVo){
        PageHelper.startPage(pageNum,pageSize);
        List<LogVo> list = logMapper.selectLogList(logVo);
        return new PageInfo<>(list);
    }

    public void addLog(Employee emp){
        String account = emp.getAccount();
        String password = emp.getPassword();
        Employee employee = employeeMapper.selectNameAndPwd(account);
        LogVo logVo = new LogVo();
        if (employee == null){
            logVo.setOperName(account);
            logVo.setStatus(1);
            logVo.setOperTime(new Date());
        }else {
            if (employee.getPassword().equals(password)){
                logVo.setOperName(employee.getName());
                logVo.setStatus(0);
                logVo.setOperTime(new Date());
            }else {
                logVo.setOperName(account);
                logVo.setStatus(1);
                logVo.setOperTime(new Date());
            }
        }
        logMapper.insertLog(logVo);
    }
}
