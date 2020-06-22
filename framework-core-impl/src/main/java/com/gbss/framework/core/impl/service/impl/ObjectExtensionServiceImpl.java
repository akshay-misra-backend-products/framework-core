package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.ObjectExtensionService;
import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.DynamicObject;
import org.springframework.beans.factory.annotation.Autowired;


public class ObjectExtensionServiceImpl implements ObjectExtensionService {

    @Autowired
    AttributeSchemaService attributeSchemaService;

    public void addValue(String attributeId, Object value, DynamicObject dynamicObject) {
        dynamicObject.getExtendedParameters().put(attributeId, value);
    }

    public Object getValue(String attributeId, DynamicObject dynamicObject) {
        Attribute attribute = attributeSchemaService.getAttributeById(attributeId);
        return dynamicObject.getExtendedParameters().get(attributeId);
    }
}
