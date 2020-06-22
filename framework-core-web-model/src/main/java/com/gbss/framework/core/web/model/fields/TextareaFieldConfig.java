package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class TextareaFieldConfig extends FieldConfig {

    public TextareaFieldConfig() {
        setType(FieldType.TEXTAREA.value);
        setMultiple(false);
    }
}
