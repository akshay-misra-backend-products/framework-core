package com.bss.framework.core.design.controller;

import com.bss.framework.core.design.model.Tab;
import com.bss.framework.core.design.model.TabLayout;
import com.bss.framework.core.design.model.TabLayoutConfig;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@RestController
@RequestMapping("/application/schema")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicationLayoutController {

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @GetMapping("/get/tabs")
    public List<Tab> getTabs() {
        return applicationLayoutService.getTabs();
    }

    @GetMapping(value="/get/tab/{id}")
    public Tab getTabById(String id) {
        return applicationLayoutService.getTabById(id);
    }

    @PostMapping("/add/tab")
    public Tab createTab(Tab tab) {
        return applicationLayoutService.createTab(tab);
    }

    @PutMapping(value="/update/tab")
    public Tab updateTab(Tab tab) {
        return applicationLayoutService.updateTab(tab);
    }

    @DeleteMapping(value="/delete/tab/{id}")
    public boolean deleteTab(String id) {
        return applicationLayoutService.deleteTab(id);
    }

    @GetMapping("/get/tab-layouts")
    public List<TabLayout> getTabLayouts() {
        return applicationLayoutService.getTabLayouts();
    }

    @GetMapping(value="/get/tab-layout/{id}")
    public TabLayout getTabLayoutById(String id) {
        return applicationLayoutService.getTabLayoutById(id);
    }

    @PostMapping("/add/tab-layout")
    public TabLayout createTabLayout(TabLayout tabLayout) {
        return applicationLayoutService.createTabLayout(tabLayout);
    }

    @PutMapping(value="/update/tab-layout")
    public TabLayout updateTabLayout(TabLayout tabLayout) {
        return applicationLayoutService.updateTabLayout(tabLayout);
    }

    @DeleteMapping(value="/delete/tab-layout/{id}")
    public boolean deleteTabLayout(String id) {
        return applicationLayoutService.deleteTabLayout(id);
    }

    @GetMapping("/get/tab-layout-configs")
    public List<TabLayoutConfig> getTabLayoutConfigs() {
        return applicationLayoutService.getTabLayoutConfigs();
    }

    @GetMapping(value="/get/tab-layout-config/{id}")
    public TabLayoutConfig getTabLayoutConfigById(String id) {
        return applicationLayoutService.getTabLayoutConfigById(id);
    }

    @PostMapping("/add/tab-layout-config")
    public TabLayoutConfig createTabLayoutConfig(TabLayoutConfig tabLayoutConfig) {
        return applicationLayoutService.createTabLayoutConfig(tabLayoutConfig);
    }

    @PutMapping(value="/update/tab-layout-config")
    public TabLayoutConfig updateTabLayoutConfig(TabLayoutConfig tabLayoutConfig) {
        return applicationLayoutService.updateTabLayoutConfig(tabLayoutConfig);
    }

    @DeleteMapping(value="/delete/tab-layout-config/{id}")
    public boolean deleteTabLayoutConfig(String id) {
        return applicationLayoutService.deleteTabLayoutConfig(id);
    }

}
