package com.gbss.framework.core.web.model;

public class ObjectLayoutWrapper {

    private String objectName;

    private ObjectDetailsConfig detail;

    private CompositeTableConfig children;

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

    public CompositeTableConfig getChildren() {
        return children;
    }

    public void setChildren(CompositeTableConfig children) {
        this.children = children;
    }
}
