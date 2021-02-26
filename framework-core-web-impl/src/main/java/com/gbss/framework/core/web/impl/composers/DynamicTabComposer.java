package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.impl.comparators.OrderNumberComparator;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.model.CompositeTableConfig;
import com.gbss.framework.core.web.model.DynamicTableConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        List<ObjectType> objectTypes = tab.getObjectTypes();
        Collections.sort(objectTypes, new OrderNumberComparator());
        List<DynamicTableConfig> tables = new ArrayList<>();
        for (ObjectType objectType : objectTypes) {
            tables.add(dynamicTableComposer.compose(objectType.getId(), null));// TODO: check if compose method can take object instead of id.
        }
        config.setTables(tables);
        return (T) config;
    }
}
