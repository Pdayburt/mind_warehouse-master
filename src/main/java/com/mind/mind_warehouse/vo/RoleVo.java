package com.mind.mind_warehouse.vo;

import lombok.Data;

import java.util.List;
@Data
public class RoleVo {
    private int id;
    private String name;
    private List<Integer> checkedIds;
}
