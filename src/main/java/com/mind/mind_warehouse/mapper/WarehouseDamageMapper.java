package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.WarehouseDamage;
import com.mind.mind_warehouse.vo.WarehouseDamageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseDamageMapper {
    int deleteWarehouseDamageByKey(@Param("wdid") Integer wdid);

    int insertWarehouseDamage(WarehouseDamageVo warehouseDamageVo);

    int selectStockId(Integer productId);

    WarehouseDamage selectByPrimaryKey(Integer id);

    WarehouseDamageVo selectWarehouseDamageById(@Param("wdid") Integer wdid);

    List<WarehouseDamageVo> selectAllProduct(@Param("pcode") String pcode,@Param("pname") String pname);

    List<WarehouseDamageVo> selectAllProductPlus(@Param("pcode") String pcode,@Param("pname") String pname);

    List<WarehouseDamageVo> selectProduct(@Param("pcode") String pcode,@Param("pname") String pname);

    List<WarehouseDamage> selectAll();

    List<WarehouseDamageVo> selectWarehouseDamageByCons(@Param("wdtype") Integer wdtype,@Param("wdcode") String wdcode);

    int updateWarehouseDamageByKey(WarehouseDamageVo warehouseDamageVo);

    List<WarehouseDamageVo> selectByIds(@Param("ids") int[] ids);
}