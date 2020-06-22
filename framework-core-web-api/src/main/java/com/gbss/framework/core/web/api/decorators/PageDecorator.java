package com.gbss.framework.core.web.api.decorators;

import com.gbss.framework.core.web.model.constants.Layout;

import javax.ejb.ObjectNotFoundException;

public interface PageDecorator<T> {

    T decorate(String objectTypeId, String id, Layout layout) throws ObjectNotFoundException;
}
