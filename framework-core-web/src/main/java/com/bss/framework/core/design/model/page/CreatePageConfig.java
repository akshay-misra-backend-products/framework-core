package com.bss.framework.core.design.model.page;

import com.bss.framework.core.design.model.DynamicFormConfig;

public class CreatePageConfig extends PageConfig {

    private DynamicFormConfig layoutConfig;

    public DynamicFormConfig getLayoutConfig() {
        return layoutConfig;
    }

    public void setLayoutConfig(DynamicFormConfig layoutConfig) {
        this.layoutConfig = layoutConfig;
    }
}
