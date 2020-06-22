package com.gbss.framework.core.impl.utils;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.utils.CommonEntityUtils;
import com.gbss.framework.core.model.entities.Base;
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
