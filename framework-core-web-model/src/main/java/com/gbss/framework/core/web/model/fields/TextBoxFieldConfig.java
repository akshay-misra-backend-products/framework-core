package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class TextBoxFieldConfig extends FieldConfig {

    public TextBoxFieldConfig() {
        setType(FieldType.TEXT.value);
        setMultiple(false);
    }
}
