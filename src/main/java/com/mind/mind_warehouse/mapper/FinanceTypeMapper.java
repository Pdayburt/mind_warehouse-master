package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.FinanceType;
import com.mind.mind_warehouse.vo.FinancialCategoryVo;
import com.mind.mind_warehouse.vo.WarehouseMoveVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByFinancialCategoryId(Integer ftid);

    FinancialCategoryVo selectFinancialCategoryById(Integer ftid);

    int insert(FinanceType record);


    int insertFinancialCategory(FinancialCategoryVo financialCategoryVo);

    FinanceType selectByPrimaryKey(Integer id);

    List<FinanceType> selectAll();

    int updateByPrimaryKey(FinanceType record);

    int updateByFinancialCategoryId(FinancialCategoryVo financialCategoryVo);

    List<FinancialCategoryVo> selectByIds(@Param("ids") int[] ids);
}