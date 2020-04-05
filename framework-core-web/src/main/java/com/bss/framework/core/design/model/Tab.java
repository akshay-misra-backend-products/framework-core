package com.bss.framework.core.design.model;

import com.bss.framework.core.schema.model.Base;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Document(collection="tabs")
public class Tab extends Base {

    private String icon;

    private String tabLayoutId;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTabLayoutId() {
        return tabLayoutId;
    }

    public void setTabLayoutId(String tabLayoutId) {
        this.tabLayoutId = tabLayoutId;
    }
}
