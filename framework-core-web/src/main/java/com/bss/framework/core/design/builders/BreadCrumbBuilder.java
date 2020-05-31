package com.bss.framework.core.design.builders;

import com.bss.framework.core.design.model.BreadCrumbConfig;
import org.springframework.stereotype.Service;

@Service
public class BreadCrumbBuilder implements Builder<BreadCrumbConfig> {

    String icon;

    String route;

    String name;

    boolean dummy;

    public BreadCrumbBuilder setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public BreadCrumbBuilder setRoute(String route) {
        this.route = route;
        return this;
    }

    public BreadCrumbBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BreadCrumbBuilder setDummy(boolean dummy) {
        this.dummy = dummy;
        return this;
    }

    @Override
    public BreadCrumbBuilder createBuilder() {
        return new BreadCrumbBuilder();
    }

    @Override
    public BreadCrumbConfig build() {
        BreadCrumbConfig breadCrumb = new BreadCrumbConfig();
        breadCrumb.setIcon(this.icon);
        breadCrumb.setName(this.name);
        breadCrumb.setRoute(this.route);
        breadCrumb.setDummy(this.dummy);

        return breadCrumb;
    }
}
