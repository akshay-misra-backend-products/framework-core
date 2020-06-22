package com.gbss.framework.core.web.model.page;

import com.gbss.framework.core.web.model.DynamicFormConfig;

public class CreatePageConfig extends PageConfig {

    private DynamicFormConfig layoutConfig;

    public DynamicFormConfig getLayoutConfig() {
        return layoutConfig;
    }

    public void setLayoutConfig(DynamicFormConfig layoutConfig) {
        this.layoutConfig = layoutConfig;
    }
}
