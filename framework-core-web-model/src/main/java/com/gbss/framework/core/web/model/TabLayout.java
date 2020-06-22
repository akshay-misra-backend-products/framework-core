package com.gbss.framework.core.web.model;

import com.gbss.framework.core.model.entities.Base;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Document(collection="tabLayouts")
public class TabLayout extends Base {

    @DBRef(lazy = true)
    private List<TabLayoutConfig> tabLayoutConfigs;

    public List<TabLayoutConfig> getTabLayoutConfigs() {
        return tabLayoutConfigs;
    }

    public void setTabLayoutConfigs(List<TabLayoutConfig> tabLayoutConfigs) {
        this.tabLayoutConfigs = tabLayoutConfigs;
    }
}
