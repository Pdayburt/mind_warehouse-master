package com.mind.mind_warehouse.service;

import com.mind.mind_warehouse.entity.AuditLog;
import com.mind.mind_warehouse.mapper.AuditLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    AuditLogMapper auditLogMapper;

    public int addLog(AuditLog log) {
        return auditLogMapper.insert(log);
    }
}
