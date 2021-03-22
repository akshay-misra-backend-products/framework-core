package com.gbss.framework.core.web.api.composers;


import com.gbss.framework.core.web.model.constants.Layout;

import javax.ejb.ObjectNotFoundException;

public interface LayoutComposer <T> {

    T compose(String parentObjectTypeId,
              String parentId,
              String objectTypeId,
              String objectId,
              Layout layout) throws ObjectNotFoundException;
}