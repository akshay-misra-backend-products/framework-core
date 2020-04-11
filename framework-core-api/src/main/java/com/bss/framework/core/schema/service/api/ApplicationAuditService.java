package com.bss.framework.core.schema.service.api;

import com.bss.framework.core.schema.model.Base;

public interface ApplicationAuditService {

    void handleAudit(Base fromDB, Base fromUI);
}
