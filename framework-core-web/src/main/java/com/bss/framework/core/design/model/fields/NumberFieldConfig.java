package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

public class NumberFieldConfig extends FieldConfig {

    public NumberFieldConfig() {
        setType(FieldType.NUMBER.value);
        setMultiple(false);
    }
}
