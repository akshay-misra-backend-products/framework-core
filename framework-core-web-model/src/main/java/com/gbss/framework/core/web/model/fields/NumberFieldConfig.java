package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class NumberFieldConfig extends FieldConfig {

    public NumberFieldConfig() {
        setType(FieldType.NUMBER.value);
        setMultiple(false);
    }
}
