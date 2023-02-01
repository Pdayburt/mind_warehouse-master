package com.mind.mind_warehouse.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class MenuVo  {
    private int id;
    private String name;
    private String url;
    private List<MenuVo> children;
}
