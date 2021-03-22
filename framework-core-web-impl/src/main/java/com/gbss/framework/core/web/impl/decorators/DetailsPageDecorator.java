package com.gbss.framework.core.web.impl.decorators;

import com.gbss.framework.core.web.impl.composers.ObjectDetailsComposer;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.page.DetailsPageConfig;
import com.gbss.framework.core.web.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class DetailsPageDecorator extends AbstractPageDecorator<DetailsPageConfig> {

    @Autowired
    ObjectDetailsComposer objectDetailsComposer;

    @Override
    public DetailsPageConfig decorate(String parentObjectTypeId,
                                      String parentId,
                                      String objectTypeId,
                                      String objectId,
                                      Layout layout) throws ObjectNotFoundException {
        DetailsPageConfig pageConfig = super.decorate(parentObjectTypeId, parentId, objectTypeId, objectId, layout);
        pageConfig.setLayoutConfig(objectDetailsComposer.compose(parentObjectTypeId, parentId, objectTypeId, objectId));

        return pageConfig;
    }

    @Override
    protected PageConfig getPageModel() {
        return new DetailsPageConfig();
    }
}
