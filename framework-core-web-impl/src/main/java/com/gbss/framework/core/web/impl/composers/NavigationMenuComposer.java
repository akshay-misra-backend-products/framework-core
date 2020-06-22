package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.impl.converters.NavigationItemConverter;
import com.gbss.framework.core.web.model.NavItemConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import com.gbss.framework.core.web.model.fields.CompositeMenuConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationMenuComposer implements Composer<CompositeMenuConfig> {

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    NavigationItemConverter navigationItemConverter;

    @Override
    public CompositeMenuConfig compose(String objectTypeId, String objectId) {
        CompositeMenuConfig config = new CompositeMenuConfig();

        List<NavigationTab> tabs =  applicationLayoutService.getNavigationTabByParentId(null);
        System.out.println("******* NavigationMenuComposer, tabs: " +  tabs);
        List<NavItemConfig> items = navigationItemConverter.convert(tabs);
        System.out.println("******* NavigationMenuComposer, items: " +  items);
        config.setItems(items);

        return config;
    }
}
