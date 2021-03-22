package com.gbss.framework.core.web.impl.service;

import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.model.TabLayout;
import com.gbss.framework.core.web.model.TabLayoutConfig;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.fields.CompositeMenuConfig;
import com.gbss.framework.core.web.model.page.PageConfig;
import com.gbss.framework.core.web.impl.repositories.TabLayoutConfigRepository;
import com.gbss.framework.core.web.impl.repositories.TabLayoutRepository;
import com.gbss.framework.core.web.impl.repositories.TabRepository;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.impl.service.impl.ApplicationAuditServiceImpl;
import com.gbss.framework.core.web.impl.composers.CompositePageComposer;
import com.gbss.framework.core.web.impl.composers.NavigationMenuComposer;
import com.gbss.framework.core.web.model.NavigationTab;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Service
public class ApplicationLayoutServiceImpl extends ApplicationAuditServiceImpl implements ApplicationLayoutService {

    @Autowired
    TabRepository tabRepository;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Autowired
    TabLayoutRepository tabLayoutRepository;

    @Autowired
    TabLayoutConfigRepository tabLayoutConfigRepository;

    @Autowired
    CompositePageComposer compositePageComposer;

    @Autowired
    NavigationMenuComposer navigationMenuComposer;

    @Override
    public List<NavigationTab> getTabs() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return tabRepository.findAll(sortByName);
    }

    @Override
    public List<NavigationTab> getNavigationTabByParentId(String parentId) {
        return tabRepository.findByParentId(parentId);
    }

    @Override
    public NavigationTab getTabById(String id) {
        System.out.println("... getTabById, id: "+id);
        return tabRepository.findById(id).get();
    }

    @Override
    public NavigationTab createTab(NavigationTab tab) {
        System.out.println("... createTab, tab: "+tab);
        tab.setObjectTypeId(SystemConstants.ObjectTypes.NAVIGATION_TAB);
        NavigationTab tabDB = tabRepository.save(tab);
        List<ObjectType> objectTypes = tabDB.getObjectTypes();
        System.out.println("........ createTab, tabDB.objectTypes: "+ objectTypes);
        if (CollectionUtils.isNotEmpty(objectTypes)) {
            objectTypes.stream().forEach(objectType -> {
                objectType.setTabId(tabDB.getId());
                attributeSchemaService.updateObjectType(objectType);
            });
        }

        return tabDB;
    }

    @Override
    public NavigationTab updateTab(NavigationTab tab) {
        System.out.println("... updateTab, tab: "+tab);
        Optional<NavigationTab> tabOp = tabRepository.findById(tab.getId());
        handleAudit(tabOp.get(), tab);
        return tabRepository.save(tab);
    }

    @Override
    public boolean deleteTab(String id) {
        System.out.println("... deleteTab, id: "+id);
        Optional<NavigationTab> objectType = tabRepository.findById(id);
        if (objectType != null) {
            tabRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<TabLayout> getTabLayouts() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
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
        Optional<TabLayout> tabLayoutOp = tabLayoutRepository.findById(tabLayout.getId());
        handleAudit(tabLayoutOp.get(), tabLayout);
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
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
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
        Optional<TabLayoutConfig> tabLayoutConfigOp = tabLayoutConfigRepository.findById(tabLayoutConfig.getId());
        handleAudit(tabLayoutConfigOp.get(), tabLayoutConfig);
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

    @Override
    public PageConfig getPageContentConfig(String parentObjectTypeId,
                                           String parentId,
                                           String objectTypeId,
                                           String objectId,
                                           Layout layout) throws ObjectNotFoundException {
        return compositePageComposer.compose(parentObjectTypeId, parentId, objectTypeId, objectId, layout);
    }

    @Override
    public CompositeMenuConfig loadMenuItemsConfig() {
        return navigationMenuComposer.compose(null, null, null, null);
    }
}
