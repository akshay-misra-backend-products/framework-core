package com.gbss.framework.core.web.impl.builders;

import com.gbss.framework.core.web.api.builders.Builder;
import com.gbss.framework.core.web.model.NavItemConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavItemBuilder  implements Builder<NavItemConfig> {

    private String id;

    private String href;

    private String objectTypeId;

    private String parent;

    private String name;

    private String icon;

    private List<NavItemConfig> items;

    private boolean dummy;

    public NavItemBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public NavItemBuilder setHref(String href) {
        this.href = href;
        return this;
    }

    public NavItemBuilder setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
        return this;
    }

    public NavItemBuilder setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public NavItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public NavItemBuilder setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public NavItemBuilder setItems(List<NavItemConfig> items) {
        this.items = items;
        return this;
    }

    public NavItemBuilder setDummy(boolean dummy) {
        this.dummy = dummy;
        return this;
    }

    @Override
    public NavItemBuilder createBuilder() {
        return new NavItemBuilder();
    }

    @Override
    public NavItemConfig build() {
        NavItemConfig item = new NavItemConfig();
        item.setId(this.id);
        item.setHref(this.href);
        item.setObjectTypeId(this.objectTypeId);
        item.setParent(this.parent);
        item.setName(this.name);
        item.setIcon(this.icon);
        item.setDummy(this.dummy);
        item.setItems(this.items);

        return item;
    }
}
