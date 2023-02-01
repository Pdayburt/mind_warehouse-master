package com.mind.mind_warehouse.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class FinanceAccountsReceivePay {
    private Integer id;
    private String name;
    private String serialNumber;

    private Integer financeTypeId;

    private Integer relatedId;

    private BigDecimal moneyAccount;

    private BigDecimal moneyPay;

    private BigDecimal moneyRemainder;
    private Date deadLine;

    private Byte type;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Byte isDelete;

}