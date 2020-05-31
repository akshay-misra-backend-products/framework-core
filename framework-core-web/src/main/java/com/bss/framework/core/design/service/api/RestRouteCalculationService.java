package com.bss.framework.core.design.service.api;

import com.bss.framework.core.design.model.BreadCrumbConfig;
import com.bss.framework.core.schema.model.Base;

import java.util.List;

public interface RestRouteCalculationService {

    String getLoadAllAPI(Base base);

    String getLoadByIdAPI(Base base);

    String getPreviousRoute(Base base);

    String getPreviousRoute(String objectTypeId);

    List<BreadCrumbConfig> getBreadcrumbs();

    String getObjectLink(Base base);

    String getObjectDetailsRoute(Base base);

    String getNavItemRoute(Base base);
}
