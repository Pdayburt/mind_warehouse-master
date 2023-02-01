package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseInOut;
import com.mind.mind_warehouse.entity.WarehouseOut;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import com.mind.mind_warehouse.vo.WarehouseOutVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
@Repository
public interface WarehouseOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseOut record);

    WarehouseOutVo selectByPrimaryKey(Integer id);

    List<WarehouseOut> selectAll();

    int updateByPrimaryKey(WarehouseOutVo record);

    List<WarehouseOutVo> selectWareOutByCons(@Param("orderNum") String orderNum,@Param("cTel") String tel);

    void insertOutProduct(@Param("inOutId") Integer inOutId, @Param("productId") Integer productId, @Param("changeNum") Integer changeNum);

    void insertWarehouseOut(WarehouseOutVo warehouseOutVo);

    Integer selectProductStockByKey(Integer id);

    void insertCustomerInfo(WarehouseOutVo warehouseOutVo);

    int deleteWareOutAndProduct(Integer id);

    void deleteWareOut(String changeId);


    void deleteCustomerOrder(Integer id);

    @Select("select * from mw_warehouse_out where id = #{id,jdbcTypeName=INTEGER}")
    WarehouseOut selectWareOutByKey(Integer id);

    void insertWarehouseOut(@Param("orderNum")String orderNum,@Param("changeId") String changeId,@Param("formEmpId") Integer formEmpId);

    WarehouseOutVo selectWareListByKey(Integer id);


    WarehouseMoveVo selectDelivery();

    WarehouseMoveVo selectReadyForDelivery();

    int deleteBatchByKeys(int[] ids);

    void deleteBatchWareIn(int[] ids);
}