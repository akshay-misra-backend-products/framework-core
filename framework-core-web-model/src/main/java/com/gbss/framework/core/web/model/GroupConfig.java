package com.gbss.framework.core.web.model;

import java.util.List;

public class GroupConfig {

    private String groupName;

    private List<FieldConfig> fields;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<FieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "GroupConfig{" +
                "groupName='" + groupName + '\'' +
                ", fields=" + fields +
                '}';
    }
}
