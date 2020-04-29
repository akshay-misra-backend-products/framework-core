package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

public class TextareaFieldConfig extends FieldConfig {

    public TextareaFieldConfig() {
        setType(FieldType.TEXTAREA.value);
        setMultiple(false);
    }
}
