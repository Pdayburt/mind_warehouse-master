package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.vo.StockListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockListMapper {
    List<StockListVo> selectProductStockListByCons(@Param("pduCode") String pduCode,@Param("pduName") String pduName, @Param("id") Integer id);
}
