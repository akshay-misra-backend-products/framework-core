package com.gbss.framework.core.web.impl.controller;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.DynamicObjectsService;
import com.gbss.framework.core.model.entities.DynamicObject;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.impl.composers.CompositePageComposer;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.fields.CompositeMenuConfig;
import com.gbss.framework.core.web.model.page.PageConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Autowired
    DynamicObjectsService dynamicObjectsService;

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

    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/details/{parentObjectTypeId}/{parentId}/{objectTypeId}/{objectId}")
    public PageConfig loadObjectDetails(@PathVariable("parentObjectTypeId") String parentObjectTypeId,
                                        @PathVariable("parentId") String parentId,
                                        @PathVariable("objectTypeId") String objectTypeId,
                                        @PathVariable("objectId") String objectId) throws ObjectNotFoundException {
        return applicationLayoutService.getPageContentConfig(
                parentObjectTypeId,
                parentId,
                objectTypeId,
                objectId,
                Layout.DETAILS);
    }

    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/Form/config/{parentObjectTypeId}/{parentId}/{objectTypeId}")
    public PageConfig loadFormConfig(@PathVariable("parentObjectTypeId") String parentObjectTypeId,
                                     @PathVariable("parentId") String parentId,
                                     @PathVariable("objectTypeId") String objectTypeId) throws ObjectNotFoundException {
        return applicationLayoutService.getPageContentConfig(
                parentObjectTypeId,
                parentId,
                objectTypeId,
                null,
                Layout.FORM);
    }

    //TODO: check how to handle exceptions in rest call.
    @GetMapping(value="/load/tab/config/{parentObjectTypeId}/{parentId}/{objectTypeId}/{tabId}")
    public PageConfig loadNavigationTabConfig(@PathVariable("parentObjectTypeId") String parentObjectTypeId,
                                              @PathVariable("parentId") String parentId,
                                              @PathVariable("objectTypeId") String objectTypeId,
                                              @PathVariable("tabId") String tabId) throws ObjectNotFoundException {
        System.out.println("%%%%%%%%%%%%%loadNavigationTabConfig: "
                + "parentObjectTypeId: " + parentObjectTypeId
                + ", parentId: " + parentId
                + ", objectTypeId: " + objectTypeId
                + ", objectId: " + tabId);
        return applicationLayoutService.getPageContentConfig(
                parentObjectTypeId,
                parentId,
                objectTypeId,
                tabId,
                Layout.TABLES);
    }

    @GetMapping(value="/load/navigation/menu/config")
    public CompositeMenuConfig loadMenuItemsConfig() {
        return applicationLayoutService.loadMenuItemsConfig();
    }

    @PostMapping("/{objectTypeId}/add")
    public DynamicObject createDynamicObject(@PathVariable("objectTypeId") String objectTypeId,
                                             @Valid @RequestBody String object) {
        return dynamicObjectsService.createDynamicObject(object);
    }

    @PutMapping(value="/{objectTypeId}/update")
    public DynamicObject updateDynamicObject(@Valid @RequestBody String object) {
        return dynamicObjectsService.updateDynamicObject(object);
    }

    @GetMapping("/{objectTypeId}/load/all")
    public List<DynamicObject> getDynamicObject(@PathVariable("objectTypeId") String objectTypeId) {
        return dynamicObjectsService.getDynamicObjects(objectTypeId);
    }

    @GetMapping(value="/{objectTypeId}/load/by/parent/{parentId}")
    public List<DynamicObject> getDynamicObjectsByParentId(@PathVariable("objectTypeId") String objectTypeId,
                                                          @PathVariable("parentId") String parentId) {
        return dynamicObjectsService.getDynamicObjectsByParentId(objectTypeId, parentId);
    }

}
