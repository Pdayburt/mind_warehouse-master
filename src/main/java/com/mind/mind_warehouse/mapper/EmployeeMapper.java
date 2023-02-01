package com.mind.mind_warehouse.mapper;


import com.mind.mind_warehouse.entity.Employee;
import com.mind.mind_warehouse.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    int updateByPEmployeeVo(EmployeeVo record);

   Employee selectNameAndPwd(String account);
   List<EmployeeVo> selectEmployeeByCons(@Param("account")String account,
                                         @Param("code")String code,
                                         @Param("parentId")Integer parentId);
    List<EmployeeVo> selectByIds(@Param("ids") int[] ids);
}