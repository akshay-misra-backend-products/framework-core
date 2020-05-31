package com.bss.framework.core.design.decorators;

import javax.ejb.ObjectNotFoundException;

public interface PageDecorator<T> {

    T decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException;
}
