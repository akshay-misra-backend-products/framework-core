package com.bss.framework.core.design.decorators.impl;

import com.bss.framework.core.design.composers.BreadCrumbComposer;
import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.decorators.PageDecorator;
import com.bss.framework.core.design.model.CompositeBreadCrumbConfig;
import com.bss.framework.core.design.model.page.PageConfig;
import com.bss.framework.core.schema.factory.ObjectTypeRepositoryMapperFactory;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.utils.CommonEntityUtils;
import com.bss.framework.core.schema.utils.EntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ejb.ObjectNotFoundException;

abstract class AbstractPageDecorator<T extends PageConfig> implements PageDecorator<T> {

    @Autowired
    BreadCrumbComposer breadCrumbComposer;

    @Autowired
    CommonEntityUtils commonEntityUtils;

    @Autowired
    EntityBuilder entityBuilder;

    @Override
    public T decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException {
        PageConfig pageConfig = getPageModel();
        if (id != null) {
            Base base = entityBuilder.getObjectByChildOrCurrentOT(objectTypeId, id);
            pageConfig.setName(commonEntityUtils.getName(base));
        }
        CompositeBreadCrumbConfig breadCrumbConfig = breadCrumbComposer.compose(objectTypeId, id, layout);
        pageConfig.setBreadCrumbConfig(breadCrumbConfig);

        return (T) pageConfig;
    }

    protected abstract PageConfig getPageModel();
}
