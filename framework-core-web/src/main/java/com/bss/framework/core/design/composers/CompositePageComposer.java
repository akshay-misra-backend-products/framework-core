package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.decorators.Layout;
import com.bss.framework.core.design.factory.PageLayoutDecoratorsFactory;
import com.bss.framework.core.design.model.page.PageConfig;
import com.bss.framework.core.schema.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.ObjectNotFoundException;

@Service
public class CompositePageComposer implements LayoutComposer<PageConfig> {

    @Autowired
    PageLayoutDecoratorsFactory pageLayoutDecoratorsFactory;

    @Override
    public PageConfig compose(String objectTypeId, String objectId, Layout layout) throws ObjectNotFoundException {
        if (SystemConstants.Objects.FAKE_OBJECT.equals(objectId)) {
            objectId = null;
        }
        return (PageConfig) pageLayoutDecoratorsFactory
                .getPageDecoratorByLayout(layout)
                .decorate(objectTypeId, objectId, layout);
    }
}
