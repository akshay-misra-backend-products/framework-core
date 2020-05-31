package com.bss.framework.core.design.model;

import java.util.List;

public class CompositeBreadCrumbConfig {

    List<BreadCrumbConfig> breadCrumbs;

    public List<BreadCrumbConfig> getBreadCrumbs() {
        return breadCrumbs;
    }

    public void setBreadCrumbs(List<BreadCrumbConfig> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }
}
