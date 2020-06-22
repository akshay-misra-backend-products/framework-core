package com.gbss.framework.core.web.model;

import java.util.List;

public class CompositeTableConfig {

    List<DynamicTableConfig> tables;

    public List<DynamicTableConfig> getTables() {
        return tables;
    }

    public void setTables(List<DynamicTableConfig> tables) {
        this.tables = tables;
    }
}
