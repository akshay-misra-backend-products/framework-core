package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

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
