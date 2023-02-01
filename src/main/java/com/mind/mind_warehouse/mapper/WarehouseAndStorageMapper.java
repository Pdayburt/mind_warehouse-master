package com.mind.mind_warehouse.mapper;


import com.mind.mind_warehouse.vo.ShowWarehouseAndStorageVo;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseAndStorageMapper {

    ShowWarehouseAndStorageVo selectWarehouseAndStorage(int id);

}
