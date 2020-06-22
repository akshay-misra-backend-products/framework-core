package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class EmailFieldConfig extends FieldConfig {

    public EmailFieldConfig() {
        setType(FieldType.EMAIL.value);
    }
}
