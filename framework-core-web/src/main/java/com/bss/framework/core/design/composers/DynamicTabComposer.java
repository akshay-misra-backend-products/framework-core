package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.model.CompositeTableConfig;
import com.bss.framework.core.design.model.DynamicTableConfig;
import com.bss.framework.core.design.model.NavigationTab;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import com.bss.framework.core.schema.model.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicTabComposer<T extends CompositeTableConfig> implements Composer<T> {

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    DynamicTableComposer dynamicTableComposer;

    @Override
    public T compose(String objectTypeId, String tabId) {
        CompositeTableConfig config = new CompositeTableConfig();
        NavigationTab tab = applicationLayoutService.getTabById(tabId);
        List<DynamicTableConfig> tables = new ArrayList<>();
        for (ObjectType objectType : tab.getObjectTypes()) {
            tables.add(dynamicTableComposer.compose(objectType.getId(), null));// TODO: check if compose method can take object instead of id.
        }
        config.setTables(tables);
        return (T) config;
    }
}
