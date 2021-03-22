package com.gbss.framework.core.web.api.decorators;

import com.gbss.framework.core.web.model.constants.Layout;

import javax.ejb.ObjectNotFoundException;

public interface PageDecorator<T> {

    T decorate(String parentObjectTypeId,
               String parentId,
               String objectTypeId,
               String objectId,
               Layout layout) throws ObjectNotFoundException;
}
