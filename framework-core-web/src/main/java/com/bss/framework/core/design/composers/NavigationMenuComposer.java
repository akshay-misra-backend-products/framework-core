package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.converters.NavigationItemConverter;
import com.bss.framework.core.design.model.NavItemConfig;
import com.bss.framework.core.design.model.NavigationTab;
import com.bss.framework.core.design.model.fields.CompositeMenuConfig;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
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
