package com.bss.framework.core.design.decorators.impl;

import com.bss.framework.core.design.composers.ObjectDetailsComposer;
import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.model.page.DetailsPageConfig;
import com.bss.framework.core.design.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class DetailsPageDecorator extends AbstractPageDecorator<DetailsPageConfig> {

    @Autowired
    ObjectDetailsComposer objectDetailsComposer;

    @Override
    public DetailsPageConfig decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException {
        DetailsPageConfig pageConfig = super.decorate(objectTypeId, id, layout);
        pageConfig.setLayoutConfig(objectDetailsComposer.compose(objectTypeId, id));

        return pageConfig;
    }

    @Override
    protected PageConfig getPageModel() {
        return new DetailsPageConfig();
    }
}
