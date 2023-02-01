package com.mind.mind_warehouse.vo;

import com.mind.mind_warehouse.entity.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseVo {
    Integer id;
    String name;
    List<Storage> children;
}
