package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.impl.comparators.OrderNumberComparator;
import com.gbss.framework.core.model.constants.SystemConstants;
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

    @Autowired
    EntityBuilder entityBuilder;

    @Override
    public T compose(String parentObjectTypeId,
                     String parentId,
                     String objectTypeId,
                     String objectId) {
        CompositeTableConfig config = new CompositeTableConfig();
        NavigationTab tab = applicationLayoutService.getTabById(objectId);
        List<ObjectType> objectTypes = tab.getObjectTypes();
        Collections.sort(objectTypes, new OrderNumberComparator());
        List<DynamicTableConfig> tables = new ArrayList<>();
        for (ObjectType objectType : objectTypes) {
            tables.add(dynamicTableComposer.compose(
                    tab.getModule() == null ? objectTypeId : tab.getModule().getObjectTypeId(),
                    tab.getModule() == null ? objectId : tab.getModule().getId(),
                    objectType.getId(),
                    SystemConstants.Objects.FAKE_OBJECT));
        }
        config.setTables(tables);
        return (T) config;
    }
}
