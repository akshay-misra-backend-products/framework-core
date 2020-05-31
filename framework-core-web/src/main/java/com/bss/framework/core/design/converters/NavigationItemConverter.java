package com.bss.framework.core.design.converters;

import com.bss.framework.core.design.builders.NavItemBuilder;
import com.bss.framework.core.design.model.NavItemConfig;
import com.bss.framework.core.design.model.NavigationTab;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import com.bss.framework.core.schema.utils.CommonEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavigationItemConverter implements Converter<NavItemConfig, NavigationTab> {

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    CommonEntityUtils commonEntityUtils;

    @Autowired
    NavItemBuilder builder;

    @Override
    public NavItemConfig convert(NavigationTab input) {
        System.out.println("******* NavigationItemConverter, tab: " +  input);
        return this.builder.createBuilder()
                .setId(input.getId())
                .setObjectTypeId(input.getObjectTypeId())
                .setParent(input.getParentId())
                .setName(this.commonEntityUtils.getName(input))
                .setIcon(input.getIcon())
                .setItems(getChildItems(input))
                .setDummy(input.isContainer())
                .build();

    }

    @Override
    public List<NavItemConfig> convert(List<NavigationTab> tabs) {
        return tabs
                .stream()
                .map(tab -> this.convert(tab))
                .collect(Collectors.toList());
    }

    private List<NavItemConfig> getChildItems(NavigationTab input) {
        return this.convert(
                this.applicationLayoutService
                        .getNavigationTabByParentId(input.getId())
        );
    }

}
