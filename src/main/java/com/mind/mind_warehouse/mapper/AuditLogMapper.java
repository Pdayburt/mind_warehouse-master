package com.mind.mind_warehouse.mapper;

import com.mind.mind_warehouse.entity.AuditLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuditLog record);

    AuditLog selectByPrimaryKey(Integer id);

    List<AuditLog> selectAll();

    int updateByPrimaryKey(AuditLog record);
}