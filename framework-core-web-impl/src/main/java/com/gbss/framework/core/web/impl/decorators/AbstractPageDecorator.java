package com.gbss.framework.core.web.impl.decorators;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.web.api.decorators.PageDecorator;
import com.gbss.framework.core.web.impl.composers.BreadCrumbComposer;
import com.gbss.framework.core.web.model.CompositeBreadCrumbConfig;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.page.PageConfig;
import com.gbss.framework.core.api.utils.CommonEntityUtils;
import com.gbss.framework.core.api.utils.EntityBuilder;
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
    public T decorate(String parentObjectTypeId,
                      String parentId,
                      String objectTypeId,
                      String objectId,
                      Layout layout) throws ObjectNotFoundException {
        PageConfig pageConfig = getPageModel();
        if (objectId != null && !Layout.FORM.equals(layout)) {
            Base base = entityBuilder.getObjectById(objectTypeId, objectId);
            pageConfig.setName(commonEntityUtils.getName(base));
        }
        CompositeBreadCrumbConfig breadCrumbConfig = breadCrumbComposer.compose(
                parentObjectTypeId,
                parentId,
                objectTypeId,
                objectId,
                layout);
        pageConfig.setBreadCrumbConfig(breadCrumbConfig);

        return (T) pageConfig;
    }

    protected abstract PageConfig getPageModel();
}
