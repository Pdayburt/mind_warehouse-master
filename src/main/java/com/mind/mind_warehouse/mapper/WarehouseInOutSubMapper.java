package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import com.mind.mind_warehouse.vo.QuarterVo;
import com.mind.mind_warehouse.vo.WarehouseInOutSubVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WarehouseInOutSubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseInOutSub record);

    WarehouseInOutSub selectByPrimaryKey(Integer id);

    List<WarehouseInOutSub> selectAll();

    int updateByPrimaryKey(WarehouseInOutSub record);

    WarehouseInOutSubVo selectOutNum();

    List<QuarterVo> selectQuarterByLikeIn(@Param("pcode") String productCode, @Param("pname") String productName,
                                          @Param("wname") String warehouseName);

    List<QuarterVo> selectQuarterByLikeOut(@Param("pcode") String productCode,@Param("pname") String productName,
                                           @Param("wname") String warehouseName);
}