package com.mind.mind_warehouse.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.entity.ProductType;
import com.mind.mind_warehouse.result.ResultResponse;
import com.mind.mind_warehouse.service.EmployeeService;
import com.mind.mind_warehouse.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("Employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/queryEmployeeByCons")
    public ResultResponse<PageInfo<EmployeeVo>> queryEmployeeByCons(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                                    @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                                                                    String account,String code,Integer parentId){
        ResultResponse<PageInfo<EmployeeVo>> result=null;
        try {
            PageInfo<EmployeeVo> employeeVoPageInfo = employeeService.queryEmployeeByCons(pageNum, pageSize, account, code, parentId);
            result=new ResultResponse<>(200,"查询员工成功",employeeVoPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"查询员工成功");
        }
        return result;
    }


    @PostMapping("/addEmployee")
    public ResultResponse<Employee> addEmployee(@RequestBody Employee employee){
        ResultResponse<Employee> result=null;
        try {
            employeeService.add(employee);
            result=new ResultResponse<>(200,"增加员工成功", employee);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"增加员工失败");
        }
        return result;
    }
    @DeleteMapping("/deleteEmployee")
    public ResultResponse<Void> deleteEmployee(@RequestParam("id") Integer id){
        ResultResponse<Void>  result=null;
        try {
            employeeService.removeByPrimaryKey(id);
            result=new ResultResponse<>(200,"删除员工成功");
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"删除员工失败");
        }
        return result;
    }
    @PutMapping("/modifyEmployee")
    public ResultResponse<EmployeeVo> modifyEmployee(@RequestBody EmployeeVo employeeVo){
        ResultResponse<EmployeeVo>  result=null;
        try {
            employeeService.modifyByPrimaryKey(employeeVo);
            result=new ResultResponse<>(200,"更新员工成功",employeeVo);
        } catch (Exception e) {
            e.printStackTrace();
            result=new ResultResponse<>(201,"更新员工失败");
        }
        return result;
    }
    private  String path="/opt/homebrew/var/www/image/warehouse/";
    @PostMapping("/upload")
    public ResultResponse<String> upload(@RequestParam(value = "employeeImg") MultipartFile multipartFile) throws IOException {
        ResultResponse<String> result = null;
        try {
            String filename = multipartFile.getOriginalFilename();
            filename= UUID.randomUUID().toString().replaceAll("-","")+filename.substring(filename.lastIndexOf("."));
            System.out.println("上传图片的名字为："+filename);
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            File f = new File(file, filename);
            multipartFile.transferTo(f);
            result = new ResultResponse<>(200,"上传成功",filename);
        } catch (IOException e) {
            e.printStackTrace();
            result = new ResultResponse<>(201,"上传失败");
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResultResponse<>(202,"系统错误！");
        }
        return  result ;
    }

    @GetMapping("/queryAllEmp")
    public ResultResponse<List<Employee>> queryAllEmp(){
        ResultResponse<List<Employee>> result=null;
        try {
            List<Employee> employees = employeeService.queryAll();
            result=new ResultResponse<>(200,"查询所有员工成功", employees);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultResponse<>(200, "查询所有员工失败");
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
        String name = "员工报表" + (new Date()).toString();
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //查询数据
//        List<Warehouse> list = wms.queryByIds(ids);
        List<EmployeeVo> employeeVos = employeeService.queryByIds(ids);

        OutputStream outputStream = response.getOutputStream();

        try {
            EasyExcel.write(response.getOutputStream(), EmployeeVo.class).sheet("员工报表").doWrite(employeeVos);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            outputStream.close();
        }
    }

}
