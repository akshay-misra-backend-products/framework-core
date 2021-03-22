package com.gbss.framework.core.web.model;

import java.util.List;

public class NavItemConfig {

    private String id;

    private String href;

    private String objectTypeId;

    private String parent;

    private String name;

    private String icon;

    private List<NavItemConfig> items;

    private boolean dummy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<NavItemConfig> getItems() {
        return items;
    }

    public void setItems(List<NavItemConfig> items) {
        this.items = items;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public String toString() {
        return "NavItemConfig["
                + " id: " + this.id
                + " name: " + this.name
                + ", items: " + this.items
                + ", dummy: " + this.dummy
                + " ]";
    }
}
