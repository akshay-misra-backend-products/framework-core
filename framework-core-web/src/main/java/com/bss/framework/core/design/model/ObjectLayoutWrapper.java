package com.bss.framework.core.design.model;

import java.util.List;

public class ObjectLayoutWrapper {

    private String objectName;

    private ObjectDetailsConfig detail;

    private List<DynamicTableConfig> children;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public ObjectDetailsConfig getDetail() {
        return detail;
    }

    public void setDetail(ObjectDetailsConfig detail) {
        this.detail = detail;
    }

    public List<DynamicTableConfig> getChildren() {
        return children;
    }

    public void setChildren(List<DynamicTableConfig> children) {
        this.children = children;
    }
}
