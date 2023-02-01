package com.mind.mind_warehouse.vo;

import lombok.Data;

import java.util.List;
@Data
public class ProductTypeVo {
    private int id;
    private String name;
    List<ProductTypeVo> children;

}
