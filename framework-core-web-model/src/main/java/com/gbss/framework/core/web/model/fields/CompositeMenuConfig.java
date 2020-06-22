package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.NavItemConfig;

import java.util.List;

public class CompositeMenuConfig {

    List<NavItemConfig> items;

    public List<NavItemConfig> getItems() {
        return items;
    }

    public void setItems(List<NavItemConfig> items) {
        this.items = items;
    }
}
