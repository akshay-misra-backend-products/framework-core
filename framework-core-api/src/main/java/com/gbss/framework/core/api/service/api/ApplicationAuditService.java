package com.gbss.framework.core.api.service.api;


import com.gbss.framework.core.model.entities.Base;

public interface ApplicationAuditService {

    void handleAudit(Base fromDB, Base fromUI);
}
