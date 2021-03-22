package com.gbss.framework.core.web.api.service;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.web.model.BreadCrumbConfig;

import java.util.List;

public interface RestRouteCalculationService {

    String getLoadAllAPI(Base base);

    String getLoadByIdAPI(Base base);

    String getPreviousRoute(Base base);

    String getPreviousRoute(String objectTypeId);

    List<BreadCrumbConfig> getBreadcrumbs();

    String getCreateObjectRoute(String parentObjectTypeId,
                                String parentId,
                                String objectTypeId);

    String getObjectDetailsRoute(Base base);

    String getObjectDetailsRoute(String parentObjectTypeId,
                                 String parentId,
                                 String objectTypeId,
                                 String objectId);

    String getObjectDetailsRoute(String parentObjectTypeId,
                                 String parentId,
                                 String objectTypeId);

    String getObjectDetailsRoute(String objectTypeId,
                                 String objectId);

    String getNavItemRoute(Base base);
}
