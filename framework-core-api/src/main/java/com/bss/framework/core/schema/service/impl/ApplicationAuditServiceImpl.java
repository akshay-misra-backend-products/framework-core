package com.bss.framework.core.schema.service.impl;

import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.service.api.ApplicationAuditService;

public class ApplicationAuditServiceImpl implements ApplicationAuditService {

    @Override
    public void handleAudit(Base fromDB, Base fromUI) {
        if (fromDB != null) {
            fromUI.setCreatedAt(fromDB.getCreatedAt());
            fromUI.setVersion(fromDB.getVersion());
            if (fromDB.getParentId() != null) {
                fromUI.setParentId(fromDB.getParentId());
            }
            if (fromDB.getObjectTypeId() != null) {
                fromUI.setObjectTypeId(fromDB.getObjectTypeId());
            }
        }
    }
}
