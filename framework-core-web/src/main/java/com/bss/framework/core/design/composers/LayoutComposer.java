package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.decorators.Layout;

import javax.ejb.ObjectNotFoundException;

public interface LayoutComposer <T> {

    T compose(String objectTypeId, String objectId, Layout layout) throws ObjectNotFoundException;
}