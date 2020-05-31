package com.bss.framework.core.design.controller;

import com.bss.framework.core.design.composers.CompositePageComposer;
import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.model.*;
import com.bss.framework.core.design.model.fields.CompositeMenuConfig;
import com.bss.framework.core.design.model.page.PageConfig;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ejb.ObjectNotFoundException;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@RestController
@RequestMapping("/application/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicationLayoutController {

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    CompositePageComposer compositePageComposer;

    @GetMapping("/5eaaa5862e2efbf64a9f4a5b/load/all")
    public List<NavigationTab> getTabs() {
        return applicationLayoutService.getTabs();
    }

    @GetMapping(value="/5eaaa5862e2efbf64a9f4a5b/load/by/parent/{parentId}")
    public List<NavigationTab> getNavigationTabByParentId(@PathVariable("parentId") String parentId) {
        return applicationLayoutService.getNavigationTabByParentId(parentId);
    }

    @GetMapping(value="/5eaaa5862e2efbf64a9f4a5b/load/{id}")
    public NavigationTab getTabById(@PathVariable("id") String id) {
        return applicationLayoutService.getTabById(id);
    }

    @PostMapping("/5eaaa5862e2efbf64a9f4a5b/add")
    public NavigationTab createTab(@Valid @RequestBody NavigationTab tab) {
        return applicationLayoutService.createTab(tab);
    }

    @PutMapping(value="/5eaaa5862e2efbf64a9f4a5b/update")
    public NavigationTab updateTab(@Valid @RequestBody NavigationTab tab) {
        return applicationLayoutService.updateTab(tab);
    }

    @DeleteMapping(value="/5eaaa5862e2efbf64a9f4a5b/delete/{id}")
    public boolean deleteTab(@PathVariable("id") String id) {
        return applicationLayoutService.deleteTab(id);
    }

    @GetMapping("/get/tab-layouts")
    public List<TabLayout> getTabLayouts() {
        return applicationLayoutService.getTabLayouts();
    }

    @GetMapping(value="/get/tab-layout/{id}")
    public TabLayout getTabLayoutById(@PathVariable("id") String id) {
        return applicationLayoutService.getTabLayoutById(id);
    }

    @PostMapping("/add/tab-layout")
    public TabLayout createTabLayout(@Valid @RequestBody TabLayout tabLayout) {
        return applicationLayoutService.createTabLayout(tabLayout);
    }

    @PutMapping(value="/update/tab-layout")
    public TabLayout updateTabLayout(@Valid @RequestBody TabLayout tabLayout) {
        return applicationLayoutService.updateTabLayout(tabLayout);
    }

    @DeleteMapping(value="/delete/tab-layout/{id}")
    public boolean deleteTabLayout(@PathVariable("id") String id) {
        return applicationLayoutService.deleteTabLayout(id);
    }

    @GetMapping("/get/tab-layout-configs")
    public List<TabLayoutConfig> getTabLayoutConfigs() {
        return applicationLayoutService.getTabLayoutConfigs();
    }

    @GetMapping(value="/get/tab-layout-config/{id}")
    public TabLayoutConfig getTabLayoutConfigById(@PathVariable("id") String id) {
        return applicationLayoutService.getTabLayoutConfigById(id);
    }

    @PostMapping("/add/tab-layout-config")
    public TabLayoutConfig createTabLayoutConfig(@Valid @RequestBody TabLayoutConfig tabLayoutConfig) {
        return applicationLayoutService.createTabLayoutConfig(tabLayoutConfig);
    }

    @PutMapping(value="/update/tab-layout-config")
    public TabLayoutConfig updateTabLayoutConfig(@Valid @RequestBody TabLayoutConfig tabLayoutConfig) {
        return applicationLayoutService.updateTabLayoutConfig(tabLayoutConfig);
    }

    @DeleteMapping(value="/delete/tab-layout-config/{id}")
    public boolean deleteTabLayoutConfig(@PathVariable("id") String id) {
        return applicationLayoutService.deleteTabLayoutConfig(id);
    }


    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/details/{objectTypeId}/{objectId}")
    public PageConfig loadObjectDetails(@PathVariable("objectTypeId") String objectTypeId,
                                        @PathVariable("objectId") String objectId) throws ObjectNotFoundException {
        return applicationLayoutService.getPageContentConfig(objectTypeId, objectId, Layout.DETAILS);
    }

    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/Form/config/{objectTypeId}/{objectId}")
    public PageConfig loadFormConfig(@PathVariable("objectTypeId") String objectTypeId,
                                     @PathVariable("objectId") String objectId) throws ObjectNotFoundException {
        return applicationLayoutService.getPageContentConfig(objectTypeId, objectId, Layout.FORM);
    }

    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/tab/config/{objectTypeId}/{tabId}")
    public PageConfig loadNavigationTabConfig(@PathVariable("objectTypeId") String objectTypeId,
                                                        @PathVariable("tabId") String tabId) throws ObjectNotFoundException {
        return applicationLayoutService.getPageContentConfig(objectTypeId, tabId, Layout.TABLES);
    }

    @GetMapping(value="/load/navigation/menu/config")
    public CompositeMenuConfig loadMenuItemsConfig() {
        return applicationLayoutService.loadMenuItemsConfig();
    }

}
