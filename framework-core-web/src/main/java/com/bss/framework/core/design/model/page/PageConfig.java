package com.bss.framework.core.design.model.page;

import com.bss.framework.core.design.model.CompositeBreadCrumbConfig;

public class PageConfig {

    private String name;

    private CompositeBreadCrumbConfig breadCrumbConfig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompositeBreadCrumbConfig getBreadCrumbConfig() {
        return breadCrumbConfig;
    }

    public void setBreadCrumbConfig(CompositeBreadCrumbConfig breadCrumbConfig) {
        this.breadCrumbConfig = breadCrumbConfig;
    }
}
