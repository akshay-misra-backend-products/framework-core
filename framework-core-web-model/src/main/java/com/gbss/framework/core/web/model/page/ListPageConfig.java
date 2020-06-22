package com.gbss.framework.core.web.model.page;

import com.gbss.framework.core.web.model.CompositeTableConfig;

public class ListPageConfig extends PageConfig {

    private CompositeTableConfig layoutConfig;

    public CompositeTableConfig getLayoutConfig() {
        return layoutConfig;
    }

    public void setLayoutConfig(CompositeTableConfig layoutConfig) {
        this.layoutConfig = layoutConfig;
    }
}
