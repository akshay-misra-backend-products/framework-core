package com.gbss.framework.core.web.impl.service;

import com.gbss.framework.core.impl.utils.CommonUtils;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.model.BreadCrumbConfig;
import com.gbss.framework.core.web.api.service.RestRouteCalculationService;
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

    @Autowired
    CommonUtils commonUtils;

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
    public String getCreateObjectRoute(String parentObjectTypeId,
                                       String parentId,
                                       String objectTypeId) {

        StringBuilder builder = new StringBuilder();
        builder.append("/application/create/object/");
        builder.append(parentObjectTypeId);
        builder.append("/");
        builder.append(parentId == null ? SystemConstants.Objects.PARENT_ID_FAKE : parentId);
        builder.append("/");
        builder.append(objectTypeId);
        System.out.println("$$$ getCreateObjectRoute: " + builder.toString());

        return builder.toString();
    }

    @Override
    public String getObjectDetailsRoute(Base base) {
        StringBuilder builder = new StringBuilder();
        builder.append("/application/object/details/");
        builder.append(SystemConstants.Objects.PARENT_OBJECT_TYPE_ID_FAKE);
        builder.append("/");
        builder.append(SystemConstants.Objects.PARENT_ID_FAKE);
        builder.append("/");
        builder.append(base.getObjectTypeId());
        builder.append("/");
        builder.append(base.getId());
        System.out.println("$$$ getObjectDetailsRoute: " + builder.toString());

        return builder.toString();
    }

    @Override
    public String getObjectDetailsRoute(String parentObjectTypeId,
                                        String parentId,
                                        String objectTypeId,
                                        String objectId) {
        StringBuilder builder = new StringBuilder();
        builder.append("/application/object/details/");
        builder.append(parentObjectTypeId);
        builder.append("/");
        builder.append(parentId == null ? SystemConstants.Objects.PARENT_ID_FAKE : parentId);
        builder.append("/");
        builder.append(objectTypeId);
        builder.append("/");
        builder.append(objectId);
        System.out.println("$$$ getObjectDetailsRoute: " + builder.toString());

        return builder.toString();
    }

    @Override
    public String getObjectDetailsRoute(String parentObjectTypeId, String parentId, String objectTypeId) {
        StringBuilder builder = new StringBuilder();
        builder.append("/application/object/details/");
        builder.append(parentObjectTypeId);
        builder.append("/");
        builder.append(parentId == null ? SystemConstants.Objects.PARENT_ID_FAKE : parentId);
        builder.append("/");
        builder.append(objectTypeId);
        builder.append("/");
        System.out.println("$$$ getObjectDetailsRoute: " + builder.toString());

        return builder.toString();
    }

    @Override
    public String getObjectDetailsRoute(String objectTypeId, String objectId) {
        StringBuilder builder = new StringBuilder();
        builder.append("/application/object/details/");
        builder.append(SystemConstants.Objects.PARENT_OBJECT_TYPE_ID_FAKE);
        builder.append("/");
        builder.append(SystemConstants.Objects.PARENT_ID_FAKE);
        builder.append("/");
        builder.append(objectTypeId);
        builder.append("/");
        builder.append(objectId);
        System.out.println("$$$ getObjectDetailsRoute: " + builder.toString());

        return builder.toString();
    }

    @Override
    public String getNavItemRoute(Base base) {
        //TODO: remove all angular routes from backend like "/application/navigation/*"
        StringBuilder builder = new StringBuilder();
        builder.append("/application/navigation/");
        builder.append(base.getObjectTypeId());
        builder.append("/");
        builder.append(base.getParentId() == null ? SystemConstants.Objects.PARENT_ID_FAKE : base.getParentId());
        builder.append("/");
        builder.append(base.getObjectTypeId());
        builder.append("/");
        builder.append(base.getId());
        System.out.println("$$$ getNavItemRoute: " + builder.toString());

        return builder.toString();
    }
}
