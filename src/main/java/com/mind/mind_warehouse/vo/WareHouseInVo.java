package com.mind.mind_warehouse.vo;

import com.mind.mind_warehouse.entity.Product;
import com.mind.mind_warehouse.entity.WarehouseIn;
import com.mind.mind_warehouse.entity.WarehouseInOut;
import com.mind.mind_warehouse.entity.WarehouseInOutSub;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author hnarry
 * @date 2022/6/15 16:26
 */
@Data

public class WareHouseInVo implements Serializable {

    private Integer id;
    private Integer type;
    private String orderNum;
    private Integer supplierId;


    private Integer inOutId;
    private Integer productId;
    private String changeId;
    private String num;
    private Integer changeNum;
    private String batch;
    private BigDecimal totalPrice;

    private Integer warehouseId;
    private Integer storageId;
    private Integer formEmpId;
    private Integer auditEmpId;

    private Date auditTime;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;



    private Integer sid;
    private String pname;

    private String pcode;

    private String iosBatch;
    private String standards;
    private String uname;

    private String stname;

    private String tname;

    private String mname;

    private String ename;

    private Integer status;

    private String note;


    private String aName;
    private String relatedOrderNum;
    private WarehouseInOutSub warehouseInOutSub;
    private WarehouseIn warehouseIn;

    private Integer wareHouseId;
    private WarehouseInOut warehouseInOut;

//   封装产品信息
    private List<Product> products;
//    选择的产品Id放入在集合中
    private List<Long> productIds;

    @Override
    public String toString() {
        return "WareHouseInVo{" +
                "id=" + id +
                ", type=" + type +
                ", orderNum='" + orderNum + '\'' +
                ", supplierId=" + supplierId +
                ", inOutId=" + inOutId +
                ", productId=" + productId +
                ", changeId='" + changeId + '\'' +
                ", num='" + num + '\'' +
                ", changeNum=" + changeNum +
                ", batch='" + batch + '\'' +
                ", totalPrice=" + totalPrice +
                ", warehouseId=" + warehouseId +
                ", storageId=" + storageId +
                ", formEmpId=" + formEmpId +
                ", auditEmpId=" + auditEmpId +
                ", auditTime=" + auditTime +
                ", creatTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", sid=" + sid +
                ", pname='" + pname + '\'' +
                ", pcode='" + pcode + '\'' +
                ", iosBatch='" + iosBatch + '\'' +
                ", standards='" + standards + '\'' +
                ", uname='" + uname + '\'' +
                ", stname='" + stname + '\'' +
                ", tname='" + tname + '\'' +
                ", mname='" + mname + '\'' +
                ", ename='" + ename + '\'' +
                ", status=" + status +
                ", aName='" + aName + '\'' +
                ", relatedOrderNum='" + relatedOrderNum + '\'' +
                ", warehouseInOutSub=" + warehouseInOutSub +
                ", warehouseIn=" + warehouseIn +
                ", wareHouseId=" + wareHouseId +
                ", warehouseInOut=" + warehouseInOut +
                ", products=" + products +
                ", productIds=" + productIds +
                '}';
    }

    public WareHouseInVo() {
    }

}
