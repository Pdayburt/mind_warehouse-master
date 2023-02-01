package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehousePurchaseReturn;
import com.mind.mind_warehouse.vo.PurchaseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WarehousePurchaseReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehousePurchaseReturn record);

    WarehousePurchaseReturn selectByPrimaryKey(Integer id);

    List<WarehousePurchaseReturn> selectAll();

    int updateByPrimaryKey(WarehousePurchaseReturn record);
//    Integer num,String code,String name,Integer status
    List<PurchaseVo> selectPurchaseVoByCons(@Param("num")Integer num,
                                            @Param("code")String code,
                                            @Param("name")String name,
                                            @Param("status")Integer status);
    List<PurchaseVo> selectPurchaseVoWithReturnByCons(@Param("returnNum")Integer num,
                                            @Param("num")String code,
                                            @Param("name")String name,
                                            @Param("status")Integer status);
    List<PurchaseVo> selectByIds(@Param("ids") int[] ids);


}