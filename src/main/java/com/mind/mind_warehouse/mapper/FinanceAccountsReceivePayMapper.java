package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.FinanceAccountsReceivePay;
import com.mind.mind_warehouse.vo.FinanceAccountsReceivePayVo;
import com.mind.mind_warehouse.vo.ReceivePayVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FinanceAccountsReceivePayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccountsReceivePay record);

    FinanceAccountsReceivePay selectByPrimaryKey(Integer id);

    List<FinanceAccountsReceivePay> selectAll();

    int updateByPrimaryKey(FinanceAccountsReceivePay record);
    List<FinanceAccountsReceivePayVo> selectByCons(@Param("serialNumber")Integer serialNumber,
                                                   @Param("name")String name,
                                                   @Param("financeTypeId")Integer financeTypeId,
                                                   @Param("status")Integer status,
                                                   @Param("customerName")String  customerName);
    List<FinanceAccountsReceivePayVo> selectPayByCons(@Param("serialNumber")Integer serialNumber,
                                                   @Param("name")String name,
                                                   @Param("financeTypeId")Integer financeTypeId,
                                                   @Param("status")Integer status,
                                                   @Param("supplierName")String  supplierName);
//    List<FinanceAccountsReceivePayVo> selectPayByBothName(@Param("serialNumber")Integer serialNumber,
//                                                      @Param("name")String name,
//                                                      @Param("financeTypeId")Integer financeTypeId
//                                                      );
List<ReceivePayVo> selectPayByConsBothCustomer(@Param("serialNumber")Integer serialNumber,
                                   @Param("name")String name,
                                   @Param("financeTypeId")Integer financeTypeId);
    List<ReceivePayVo> selectPayByConsBothSupplier(@Param("serialNumber")Integer serialNumber,
                                                   @Param("name")String name,
                                                   @Param("financeTypeId")Integer financeTypeId);


    Integer selectIdByTradeNo(String tradeNo);
}