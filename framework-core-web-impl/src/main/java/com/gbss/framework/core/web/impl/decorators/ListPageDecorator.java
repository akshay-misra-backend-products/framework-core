package com.gbss.framework.core.web.impl.decorators;

import com.gbss.framework.core.web.impl.composers.DynamicTabComposer;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.page.ListPageConfig;
import com.gbss.framework.core.web.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class ListPageDecorator extends AbstractPageDecorator<ListPageConfig> {

    @Autowired
    DynamicTabComposer dynamicTabComposer;


    @Override
    public ListPageConfig decorate(String parentObjectTypeId,
                                   String parentId,
                                   String objectTypeId,
                                   String objectId,
                                   Layout layout) throws ObjectNotFoundException {
        ListPageConfig pageConfig = super.decorate(parentObjectTypeId, parentId, objectTypeId, objectId, layout);
        pageConfig.setLayoutConfig(dynamicTabComposer.compose(parentObjectTypeId, parentId, objectTypeId, objectId));

        return pageConfig;
    }

    @Override
    protected PageConfig getPageModel() {
        return new ListPageConfig();
    }
}
