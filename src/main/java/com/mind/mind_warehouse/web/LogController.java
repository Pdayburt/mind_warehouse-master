package com.mind.mind_warehouse.web;

import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Customer;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.LogService;
import com.mind.mind_warehouse.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/like")
    public ResultResponse<PageInfo<LogVo>> showLike(@RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                                    String location, String name, Date time){
        ResultResponse<PageInfo<LogVo>> result = null;
        try {
            LogVo logVo = new LogVo();
            logVo.setOperLocation(location);
            logVo.setOperName(name);
            logVo.setOperTime(time);
            PageInfo<LogVo> pageInfo = logService.findAll(pageNum,pageSize,logVo);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }
}
