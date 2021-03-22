package com.gbss.framework.core.web.impl.converters;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.converters.Converter;
import com.gbss.framework.core.web.impl.builders.BreadCrumbBuilder;
import com.gbss.framework.core.web.model.BreadCrumbConfig;
import com.gbss.framework.core.web.model.NavigationTab;
import com.gbss.framework.core.web.impl.service.RestRouteCalculationServiceImpl;
import com.gbss.framework.core.api.utils.CommonEntityUtils;
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

    public BreadCrumbConfig getDetails(String parentObjectTypeId,
                                       String parentId,
                                       Base base) {
        return breadCrumbBuilder.createBuilder()
                .setName(commonEntityUtils.getName(base))
                .setRoute(restRouteCalculationService.getObjectDetailsRoute(
                        parentObjectTypeId,
                        parentId,
                        base.getObjectTypeId(),
                        base.getId()))
                .setIcon("")
                .setDummy(false)
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
