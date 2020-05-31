package com.bss.framework.core.design.converters;

import com.bss.framework.core.design.builders.BreadCrumbBuilder;
import com.bss.framework.core.design.model.BreadCrumbConfig;
import com.bss.framework.core.design.model.NavigationTab;
import com.bss.framework.core.design.service.impl.RestRouteCalculationServiceImpl;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.model.ObjectType;
import com.bss.framework.core.schema.utils.CommonEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreadCrumbConverter implements Converter<BreadCrumbConfig, ObjectType> {

    @Autowired
    BreadCrumbBuilder breadCrumbBuilder;

    @Autowired
    CommonEntityUtils commonEntityUtils;

    @Autowired
    RestRouteCalculationServiceImpl restRouteCalculationService;

    @Override
    public BreadCrumbConfig convert(ObjectType input) {
        System.out.println("******* BreadCrumbConverter, objectType: " +  input);
        return breadCrumbBuilder.createBuilder()
                .setName(commonEntityUtils.getName(input))
                .setRoute("")
                .setIcon("")
                .setDummy(false)
                .build();
    }

    @Override
    public List<BreadCrumbConfig> convert(List<ObjectType> input) {
        return input
                .stream()
                .map(tab -> this.convert(tab))
                .collect(Collectors.toList());
    }

    public BreadCrumbConfig getHome() {
        return breadCrumbBuilder.createBuilder()
                .setName("Home")
                .setRoute("/")
                .setIcon("home")
                .setDummy(false)
                .build();
    }

    public BreadCrumbConfig getDummy(Base base) {
        return breadCrumbBuilder.createBuilder()
                .setName(commonEntityUtils.getName(base))
                .setRoute("")
                .setIcon("")
                .setDummy(true)
                .build();
    }

    public BreadCrumbConfig getDetails(Base base) {
        return breadCrumbBuilder.createBuilder()
                .setName(commonEntityUtils.getName(base))
                .setRoute(restRouteCalculationService.getObjectDetailsRoute(base))
                .setIcon("")
                .setDummy(false)
                .build();
    }

    public BreadCrumbConfig getNavItem(NavigationTab navItem) {
        return breadCrumbBuilder.createBuilder()
                .setName(commonEntityUtils.getName(navItem))
                .setRoute(restRouteCalculationService.getNavItemRoute(navItem))
                .setIcon(navItem.getIcon())
                .setDummy(false)
                .build();
    }
}
