package com.gbss.framework.core.web.api.service.api;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.web.model.BreadCrumbConfig;

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
