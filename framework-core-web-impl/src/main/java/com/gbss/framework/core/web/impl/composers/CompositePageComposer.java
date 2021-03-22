package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.web.api.composers.LayoutComposer;
import com.gbss.framework.core.web.impl.factory.PageLayoutDecoratorsFactory;
import com.gbss.framework.core.web.model.constants.Layout;
import com.gbss.framework.core.web.model.page.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class CompositePageComposer implements LayoutComposer<PageConfig> {

    @Autowired
    PageLayoutDecoratorsFactory pageLayoutDecoratorsFactory;

    @Override
    public PageConfig compose(String parentObjectTypeId,
                              String parentId,
                              String objectTypeId,
                              String objectId,
                              Layout layout) throws ObjectNotFoundException {
        if (SystemConstants.Objects.FAKE_OBJECT.equals(objectId)) {
            objectId = null;
        }
        return (PageConfig) pageLayoutDecoratorsFactory
                .getPageDecoratorByLayout(layout)
                .decorate(parentObjectTypeId, parentId, objectTypeId, objectId, layout);
    }
}
