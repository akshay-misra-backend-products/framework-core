package com.bss.framework.core.schema.utils;

import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.service.api.AttributeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonEntityUtilsImpl implements CommonEntityUtils {

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Override
    public String getName(Base base) {
        return base.getPublicName() == null ? base.getName() : base.getPublicName();
    }
}
