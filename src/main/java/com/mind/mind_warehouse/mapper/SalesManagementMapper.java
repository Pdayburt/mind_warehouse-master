package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.SalesManagement;
import com.mind.mind_warehouse.vo.SalesVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesManagement record);

    SalesManagement selectByPrimaryKey(Integer id);

    List<SalesManagement> selectAll();

    int updateByPrimaryKey(SalesManagement record);

    List<SalesVo> selectAllSalesVo(@Param("orderNum")String orderNum,
                                   @Param("pcode")String pcode,
                                   @Param("pname") String pname,
                                   @Param("status")Integer status);

    List<SalesVo> selectAllReturnSalesVo(@Param("returnNum")String returnNum,
                                   @Param("pcode")String pcode,
                                   @Param("pname") String pname);

    int addTradeNo(@Param("id") int id,@Param("tradeNo") String tradeNo,@Param("updateTime") Date updateTime);

    int addReturnInNum(@Param("id") int id,@Param("returnIn") String returnIn,@Param("updateTime") Date updateTime);

    List<SalesVo> selectSalesVoByIds(@Param("ids") int[] ids);
}