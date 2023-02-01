package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseInventory;
import com.mind.mind_warehouse.vo.InventoryPlusVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseInventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseInventory record);

    WarehouseInventory selectByPrimaryKey(Integer id);

    List<WarehouseInventory> selectAll();

    int updateByPrimaryKey(WarehouseInventory record);

    //中间表添加
    int  insertInventoryAndProducts(@Param("id") Integer id,@Param("ids") List<Integer> checkedIds);
    //中间表添加产品id,数量
    int insertInventoryAndProductAndCount(@Param("id") Integer id, @Param("mapList")List<Map> mapList);

    //中间表删除
    @Delete("update mw_warehouse_inventory_product set is_deleate=1 where inventory_id=#{id}")
    int deleteInventoryAndProducts(Integer id);

    int deleteById(Integer id);

    List<WarehouseInventory> selectByLike(@Param("code") String inventoryNum,@Param("status") Integer status);

    List<InventoryPlusVo> selectInventoryPlusById(Integer id);
}