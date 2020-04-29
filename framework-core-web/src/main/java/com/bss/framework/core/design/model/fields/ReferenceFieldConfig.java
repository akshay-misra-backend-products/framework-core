package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

public class ReferenceFieldConfig extends FieldConfig {

    private String loadAPI;

    public ReferenceFieldConfig() {
        setType(FieldType.REFERENCE.value);
    }

    public String getLoadAPI() {
        return loadAPI;
    }

    public void setLoadAPI(String loadAPI) {
        this.loadAPI = loadAPI;
    }
}
