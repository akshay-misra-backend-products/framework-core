package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.api.service.api.ApplicationAuditService;
import com.gbss.framework.core.model.entities.Base;

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
