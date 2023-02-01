package com.mind.mind_warehouse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.mapper.EmpRoleMapper;
import com.mind.mind_warehouse.mapper.EmployeeMapper;
import com.mind.mind_warehouse.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hnarry
 * @date 2022/6/14 15:02
 */

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    public Employee login(String account) {
        return  employeeMapper.selectNameAndPwd(account);
    }
//    List<Employee> selectEmployeeByCons(@Param("account")String account,
//                                        @Param("code")String code,
//                                        @Param("parentId")Integer parentId
//    );
    public PageInfo<EmployeeVo> queryEmployeeByCons(Integer pageNum,Integer pageSize,String account,
                                                    String code,Integer parentId){
        PageHelper.startPage(pageNum,pageSize);
        List<EmployeeVo> employeeVos = employeeMapper.selectEmployeeByCons(account, code, parentId);
        return  new PageInfo<>(employeeVos);
    }
//    int deleteByPrimaryKey(Integer id);

    public int removeByPrimaryKey(Integer id){
        return employeeMapper.deleteByPrimaryKey(id);
    }
//    int insert(Employee record);
    public int add(Employee employee){
        return employeeMapper.insert(employee);
    }

//    Employee selectByPrimaryKey(Integer id);
    public Employee queryByPrimaryKey(Integer id){
        return employeeMapper.selectByPrimaryKey(id);
    }

//    List<Employee> selectAll();
    public List<Employee> queryAll(){
        return employeeMapper.selectAll();
    }

//    int updateByPrimaryKey(Employee record);
    @Autowired
    EmpRoleMapper empRoleMapper;
    @Transactional
    public void modifyByPrimaryKey(EmployeeVo employeeVo){

        try {
            empRoleMapper.deleteByRoleId(employeeVo.getId());
            empRoleMapper.insertRoleIdsByEmpId(employeeVo.getId(),employeeVo.getRoleIds());
            employeeMapper.updateByPEmployeeVo(employeeVo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    List<EmployeeVo> selectByIds(@Param("ids") int[] ids);
    public List<EmployeeVo> queryByIds(int[] ids){
        return  employeeMapper.selectByIds(ids);
    }

}
