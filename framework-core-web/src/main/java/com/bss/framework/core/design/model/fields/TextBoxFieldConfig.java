package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

public class TextBoxFieldConfig extends FieldConfig {

    public TextBoxFieldConfig() {
        setType(FieldType.TEXT.value);
        setMultiple(false);
    }
}
