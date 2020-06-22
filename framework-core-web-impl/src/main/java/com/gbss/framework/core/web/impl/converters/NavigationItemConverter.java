package com.gbss.framework.core.web.impl.converters;

import com.gbss.framework.core.web.api.converters.Converter;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.impl.builders.NavItemBuilder;
import com.gbss.framework.core.web.model.NavItemConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import com.gbss.framework.core.api.utils.CommonEntityUtils;
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
