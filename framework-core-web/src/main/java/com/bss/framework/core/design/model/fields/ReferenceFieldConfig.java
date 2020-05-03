package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

public class ReferenceFieldConfig extends FieldConfig {

    private String loadAPI;

    private boolean refIdAttr;

    public ReferenceFieldConfig() {
        setType(FieldType.REFERENCE.value);
    }

    public String getLoadAPI() {
        return loadAPI;
    }

    public void setLoadAPI(String loadAPI) {
        this.loadAPI = loadAPI;
    }

    public boolean isRefIdAttr() {
        return refIdAttr;
    }

    public void setRefIdAttr(boolean refIdAttr) {
        this.refIdAttr = refIdAttr;
    }
}
