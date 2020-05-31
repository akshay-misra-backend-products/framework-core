package com.bss.framework.core.design.model.page;

import com.bss.framework.core.design.model.CompositeTableConfig;

public class ListPageConfig extends PageConfig {

    private CompositeTableConfig layoutConfig;

    public CompositeTableConfig getLayoutConfig() {
        return layoutConfig;
    }

    public void setLayoutConfig(CompositeTableConfig layoutConfig) {
        this.layoutConfig = layoutConfig;
    }
}
