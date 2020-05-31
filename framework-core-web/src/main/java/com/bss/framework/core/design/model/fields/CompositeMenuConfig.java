package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.NavItemConfig;

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
