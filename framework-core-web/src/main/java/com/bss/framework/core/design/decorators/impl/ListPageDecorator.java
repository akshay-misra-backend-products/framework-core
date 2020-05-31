package com.bss.framework.core.design.decorators.impl;

import com.bss.framework.core.design.composers.DynamicTabComposer;
import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.model.page.ListPageConfig;
import com.bss.framework.core.design.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class ListPageDecorator extends AbstractPageDecorator<ListPageConfig> {

    @Autowired
    DynamicTabComposer dynamicTabComposer;


    @Override
    public ListPageConfig decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException {
        ListPageConfig pageConfig = super.decorate(objectTypeId, id, layout);
        pageConfig.setLayoutConfig(dynamicTabComposer.compose(objectTypeId, id));

        return pageConfig;
    }

    @Override
    protected PageConfig getPageModel() {
        return new ListPageConfig();
    }
}
