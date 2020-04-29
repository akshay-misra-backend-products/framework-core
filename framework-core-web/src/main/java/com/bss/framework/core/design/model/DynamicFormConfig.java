package com.bss.framework.core.design.model;

import java.util.List;

public class DynamicFormConfig {

    private String formTitle;

    private List<GroupConfig> groups;

    private String createAPI;

    private String cancelLink;

    private String objectTypeId;

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public List<GroupConfig> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupConfig> groups) {
        this.groups = groups;
    }

    public String getCreateAPI() {
        return createAPI;
    }

    public void setCreateAPI(String createAPI) {
        this.createAPI = createAPI;
    }

    public String getCancelLink() {
        return cancelLink;
    }

    public void setCancelLink(String cancelLink) {
        this.cancelLink = cancelLink;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }
}
