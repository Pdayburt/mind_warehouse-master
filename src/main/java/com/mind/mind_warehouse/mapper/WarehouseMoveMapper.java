package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.vo.SalableStockVo;
import com.mind.mind_warehouse.vo.ShowWarehouseAndStorageVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseMoveMapper {

    List<WarehouseMoveVo> selectWarehouseMoveBuyCons(@Param("moveNum") String moveNum,@Param("moveType") Integer moveType);

    List<WarehouseMoveVo> selectProductBuyCons(@Param("pname") String pname,@Param("pcode") String pcode);


    List<WarehouseMoveVo> selectAllProduct(@Param("pname") String pname,@Param("pcode") String pcode);

    WarehouseMoveVo selectMoveNumCount();

    int deleteWarehouseMoveById(int id);

    WarehouseMoveVo selectWarehouseMoveById(@Param("wwmid") int wwmid);


    List<WarehouseMoveVo> selectSalableStockByCons(@Param("pcode") String pcode,@Param("pname") String pname);


    List<WarehouseMoveVo> selectFinancialCategoryByCons(@Param("ftname") String ftname);



    int insert(WarehouseMoveVo warehouseMoveVo);

    int update(WarehouseMoveVo warehouseMoveVo);

    Integer selectStockIdByMoveId (@Param("mid") int mid);

    List<WarehouseMoveVo> selectByIds(@Param("ids") int[] ids);


    List<SalableStockVo> selectBySalableStockIds(@Param("ids") int[] ids);
}
