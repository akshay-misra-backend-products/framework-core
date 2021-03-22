package com.gbss.framework.core.web.api.service;

import com.gbss.framework.core.web.model.TabLayout;
import com.gbss.framework.core.web.model.TabLayoutConfig;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.fields.CompositeMenuConfig;
import com.gbss.framework.core.web.model.page.PageConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import org.springframework.web.bind.annotation.PathVariable;

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

    PageConfig getPageContentConfig(String parentObjectTypeId,
                                    String parentId,
                                    String objectTypeId,
                                    String objectId,
                                    Layout layout) throws ObjectNotFoundException;

    CompositeMenuConfig loadMenuItemsConfig();
}
