package com.mind.mind_warehouse.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInOutSubVo {
    private Integer id;
    private Integer inOutId;
    private String changeId;
    private Integer productId;
    private Integer changeNum;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;


}
