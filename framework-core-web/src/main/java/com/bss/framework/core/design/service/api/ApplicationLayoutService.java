package com.bss.framework.core.design.service.api;

import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.model.*;
import com.bss.framework.core.design.model.fields.CompositeMenuConfig;
import com.bss.framework.core.design.model.page.PageConfig;

import javax.ejb.ObjectNotFoundException;
import java.util.List;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
public interface ApplicationLayoutService {

    List<NavigationTab> getTabs();

    List<NavigationTab> getNavigationTabByParentId(String parentId);

    NavigationTab getTabById(String id);

    NavigationTab createTab(NavigationTab tab);

    NavigationTab updateTab(NavigationTab tab);

    boolean deleteTab(String id);

    List<TabLayout> getTabLayouts();

    TabLayout getTabLayoutById(String id);

    TabLayout createTabLayout(TabLayout tab);

    TabLayout updateTabLayout(TabLayout tab);

    boolean deleteTabLayout(String id);

    List<TabLayoutConfig> getTabLayoutConfigs();

    TabLayoutConfig getTabLayoutConfigById(String id);

    TabLayoutConfig createTabLayoutConfig(TabLayoutConfig tab);

    TabLayoutConfig updateTabLayoutConfig(TabLayoutConfig tab);

    boolean deleteTabLayoutConfig(String id);

    PageConfig getPageContentConfig(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException;

    CompositeMenuConfig loadMenuItemsConfig();
}
