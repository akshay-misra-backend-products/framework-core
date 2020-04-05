package com.bss.framework.core.design.service.api;

import com.bss.framework.core.design.model.Tab;
import com.bss.framework.core.design.model.TabLayout;
import com.bss.framework.core.design.model.TabLayoutConfig;

import java.util.List;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
public interface ApplicationLayoutService {

    List<Tab> getTabs();

    Tab getTabById(String id);

    Tab createTab(Tab tab);

    Tab updateTab(Tab tab);

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
}