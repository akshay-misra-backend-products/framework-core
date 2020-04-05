package com.bss.framework.core.design.service.impl;

import com.bss.framework.core.design.model.Tab;
import com.bss.framework.core.design.model.TabLayout;
import com.bss.framework.core.design.model.TabLayoutConfig;
import com.bss.framework.core.design.repositories.TabLayoutConfigRepository;
import com.bss.framework.core.design.repositories.TabLayoutRepository;
import com.bss.framework.core.design.repositories.TabRepository;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Service
public class ApplicationLayoutServiceImpl implements ApplicationLayoutService {

    @Autowired
    TabRepository tabRepository;

    @Autowired
    TabLayoutRepository tabLayoutRepository;

    @Autowired
    TabLayoutConfigRepository tabLayoutConfigRepository;

    @Override
    public List<Tab> getTabs() {
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
        return tabRepository.findAll(sortByName);
    }

    @Override
    public Tab getTabById(String id) {
        System.out.println("... getTabById, id: "+id);
        return tabRepository.findById(id).get();
    }

    @Override
    public Tab createTab(Tab tab) {
        System.out.println("... createTab, tab: "+tab);
        return tabRepository.save(tab);
    }

    @Override
    public Tab updateTab(Tab tab) {
        System.out.println("... updateTab, tab: "+tab);
        return tabRepository.save(tab);
    }

    @Override
    public boolean deleteTab(String id) {
        System.out.println("... deleteTab, id: "+id);
        Optional<Tab> objectType = tabRepository.findById(id);
        if (objectType != null) {
            tabRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<TabLayout> getTabLayouts() {
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
        return tabLayoutRepository.findAll(sortByName);
    }

    @Override
    public TabLayout getTabLayoutById(String id) {
        System.out.println("... getTabLayoutById, id: "+id);
        return tabLayoutRepository.findById(id).get();
    }

    @Override
    public TabLayout createTabLayout(TabLayout tabLayout) {
        System.out.println("... createTabLayout, tabLayout: "+tabLayout);
        return tabLayoutRepository.save(tabLayout);
    }

    @Override
    public TabLayout updateTabLayout(TabLayout tabLayout) {
        System.out.println("... updateTabLayout, tabLayout: "+tabLayout);
        return tabLayoutRepository.save(tabLayout);
    }

    @Override
    public boolean deleteTabLayout(String id) {
        System.out.println("... deleteTabLayout, id: "+id);
        Optional<TabLayout> objectType = tabLayoutRepository.findById(id);
        if (objectType != null) {
            tabLayoutRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<TabLayoutConfig> getTabLayoutConfigs() {
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
        return tabLayoutConfigRepository.findAll(sortByName);
    }

    @Override
    public TabLayoutConfig getTabLayoutConfigById(String id) {
        System.out.println("... getTabLayoutConfigById, id: "+id);
        return tabLayoutConfigRepository.findById(id).get();
    }

    @Override
    public TabLayoutConfig createTabLayoutConfig(TabLayoutConfig tabLayoutConfig) {
        System.out.println("... createTabLayoutConfig, tabLayoutConfig: "+tabLayoutConfig);
        return tabLayoutConfigRepository.save(tabLayoutConfig);
    }

    @Override
    public TabLayoutConfig updateTabLayoutConfig(TabLayoutConfig tabLayoutConfig) {
        System.out.println("... updateTabLayoutConfig, tabLayoutConfig: "+tabLayoutConfig);
        return tabLayoutConfigRepository.save(tabLayoutConfig);
    }

    @Override
    public boolean deleteTabLayoutConfig(String id) {
        System.out.println("... deleteTabLayoutConfig, id: "+id);
        Optional<TabLayoutConfig> objectType = tabLayoutConfigRepository.findById(id);
        if (objectType != null) {
            tabLayoutConfigRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
