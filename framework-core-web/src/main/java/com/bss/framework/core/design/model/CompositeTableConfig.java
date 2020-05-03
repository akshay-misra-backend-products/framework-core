package com.bss.framework.core.design.model;

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
