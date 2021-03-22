package com.gbss.framework.core.impl.utils;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.ModulesService;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonUtils {

    @Autowired
    ModulesService modulesService;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    public String constructApiUrl(String objectTypeId, String api) {
        String serviceApi = null;
        ObjectType objectType = attributeSchemaService.getObjectTypeById(objectTypeId);
        String serviceName = modulesService.gerCrudServiceNameByObjectType(objectType);
        serviceApi = "/" + serviceName + api;
        System.out.println("... constructApiUrl, serviceApi: " + serviceApi);

        return serviceApi;
    }

    public String constructApiUrl(ObjectType objectType, String api) {
        String serviceApi = null;
        String serviceName = modulesService.gerCrudServiceNameByObjectType(objectType);
        serviceApi = "/" + serviceName + api;
        System.out.println("... constructApiUrl, serviceApi: " + serviceApi);

        return serviceApi;
    }

    public String getFrameworkApi(String api) {
        String frameworkApi = null;
        String[] segments = api.split("/");
        System.out.println("**** $$ ### getFrameworkApi, service name: " + segments[1]);
        String service = segments[1] ;
        frameworkApi = api.replace(service, "gbss-framework-core-service");

        System.out.println("**** $$ ### getFrameworkApi, frameworkApi: " + frameworkApi);
        return frameworkApi;
    }
}
