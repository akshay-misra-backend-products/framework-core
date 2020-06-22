package com.gbss.framework.core.web.impl.service;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.model.BreadCrumbConfig;
import com.gbss.framework.core.web.api.service.api.RestRouteCalculationService;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.utils.CommonEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestRouteCalculationServiceImpl implements RestRouteCalculationService {

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Autowired
    CommonEntityUtils commonEntityUtils;

    @Override
    public String getLoadAllAPI(Base base) {
        ObjectType objectType = attributeSchemaService.getObjectTypeById(base.getObjectTypeId());
        return objectType.getLoadAPI();
    }

    @Override
    public String getLoadByIdAPI(Base base) {
        ObjectType objectType = attributeSchemaService.getObjectTypeById(base.getObjectTypeId());
        return objectType.getLoadByIdAPI();
    }

    @Override
    public String getPreviousRoute(Base base) {
        return null;
    }

    @Override
    public String getPreviousRoute(String objectTypeId) {
        return null;
    }

    @Override
    public List<BreadCrumbConfig> getBreadcrumbs() {
        return null;
    }

    @Override
    public String getObjectLink(Base base) {
        String link = "/application/";
        String name =  commonEntityUtils.getName(base);
        return link;
    }

    @Override
    public String getObjectDetailsRoute(Base base) {
        return "/application/api/load/details/"+base.getObjectTypeId()+"/"+base.getId();
    }

    @Override
    public String getNavItemRoute(Base base) {
        return "/application/navigation/"+base.getObjectTypeId()+"/"+ base.getId();
    }
}
