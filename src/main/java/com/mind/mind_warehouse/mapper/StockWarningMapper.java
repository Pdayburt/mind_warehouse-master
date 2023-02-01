package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.vo.StockWarningVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockWarningMapper {

    List<StockWarningVo> selectProductStockWarnByCons(@Param("prCode") String prCode, @Param("prName")String prName, @Param("id")Integer id);
}