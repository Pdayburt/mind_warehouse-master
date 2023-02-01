package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Customer;
import com.mind.mind_warehouse.entity.Supplier;
import com.mind.mind_warehouse.entity.Warehouse;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResultResponse<Void> add(@RequestBody Customer customer){
        ResultResponse<Void> result = null;
        try {
            customer.setCreatTime(new Date());
            customer.setUpdateTime(new Date());
            customer.setIsDelete(0);
            int i = customerService.add(customer);
            if (i > 0){
                result = new ResultResponse<>(200,"添加成功");
            }else {
                result = new ResultResponse<>(201,"添加失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"添加失败");
        }
        return result;
    }

    @DeleteMapping("/del/{id}")
    public ResultResponse<Void> del(@PathVariable Integer id){
        ResultResponse<Void> result = null;
        try {
            int i = customerService.remove(id);
            if (i == 1){
                result = new ResultResponse<>(200,"删除成功");
            }else {
                result = new ResultResponse<>(201,"删除失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"删除失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<Void> modify(@RequestBody Customer customer){
        ResultResponse<Void> result = null;
        try {
            Customer cus = customerService.findById(customer.getId());
            customer.setCreatTime(cus.getCreatTime());
            customer.setUpdateTime(new Date());
            int i = customerService.modify(customer);
            if (i == 1){
                result = new ResultResponse<>(200,"修改成功");
            }else {
                result = new ResultResponse<>(201,"修改失败");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"修改失败");
        }
        return result;
    }

    @GetMapping("/like")
    public ResultResponse<PageInfo<Customer>> showLike(@RequestParam(defaultValue = "1") Integer pageNum,
                                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                                       @RequestParam(defaultValue = "") String code,
                                                       @RequestParam(defaultValue = "") String name,
                                                       @RequestParam(defaultValue = "") String tel){
        ResultResponse<PageInfo<Customer>> result = null;
        try {
            PageInfo<Customer> pageInfo = customerService.findByLike(pageNum,pageSize,code,name,tel);
            result = new ResultResponse<>(200,"查询成功",pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

    @GetMapping("/list")
    public ResultResponse<List<Customer>> findAll(){
        ResultResponse<List<Customer>> result = null;
        try {
            List<Customer> list = customerService.findAll();
            result = new ResultResponse<>(200,"查询成功",list);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(201,"查询失败");
        }
        return result;
    }

    @GetMapping("/download")
    public void downloadExcel(@RequestParam(value = "ids") Integer[] ids, HttpServletResponse response) throws IOException {

        if (ids.length < 1) {
            return;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String name = "客户报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
        List<Customer> list = customerService.findAllByIds(ids);
        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), Customer.class).sheet("模板").doWrite(list);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }
}
