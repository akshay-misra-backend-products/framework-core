package com.bss.framework.core.design.decorators.impl;

import com.bss.framework.core.design.composers.DynamicFormComposer;
import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.model.page.CreatePageConfig;
import com.bss.framework.core.design.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class CreatePageDecorator extends AbstractPageDecorator<CreatePageConfig> {

    @Autowired
    DynamicFormComposer dynamicFormComposer;

    @Override
    public CreatePageConfig decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException {
        CreatePageConfig pageConfig = super.decorate(objectTypeId, id, layout);
        pageConfig.setLayoutConfig(dynamicFormComposer.compose(objectTypeId, id));

        return pageConfig;
    }

    @Override
    protected PageConfig getPageModel() {
        return new CreatePageConfig();
    }
}
