package com.bss.framework.core.design.model;

import java.util.List;

public class ObjectDetailsConfig {

    private String id;

    private String objectName;

    private List<GroupConfig> groups;

    private String updateAPI;

    private boolean editable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<GroupConfig> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupConfig> groups) {
        this.groups = groups;
    }

    public String getUpdateAPI() {
        return updateAPI;
    }

    public void setUpdateAPI(String updateAPI) {
        this.updateAPI = updateAPI;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
