package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseIn;
import com.mind.mind_warehouse.vo.WareHouseInVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WarehouseInMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseIn  record);

    WareHouseInVo selectByPrimaryKey(Integer id);

    List<WarehouseIn> selectAll();

    int updateByPrimaryKey(WarehouseIn record);

    List<WareHouseInVo> selectWarehouseInByCons(@Param("orderNum") String orderNum, @Param("type") Integer type,@Param("status") Integer status);

//    void insertProduct(@Param("pid") Integer id,@Param("ids") List<Long> productIds);


    void insertWareIn(WareHouseInVo wareHouseInVo);

    Integer selectProductByKey(Integer id);


    int  deleteWareIn(Integer id);


    void deleteWareByIn(Integer id);

    Integer selectIdByOrder(String orderNum);

    void insertProduct(@Param("inOutId") Integer inOutId, @Param("productId") Integer productId, @Param("changeNum") Integer changeNum,@Param("storageId") Integer storageId,@Param("batch") String batch);
//批量删除
    int deleteBatchByKeys(int[] ids);

    void deleteBatchWareIn(int[] ids);
}