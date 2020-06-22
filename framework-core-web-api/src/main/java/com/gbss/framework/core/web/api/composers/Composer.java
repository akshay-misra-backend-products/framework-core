package com.gbss.framework.core.web.api.composers;

import javax.ejb.ObjectNotFoundException;

public interface Composer<T> {

    T compose(String objectTypeId, String objectId) throws ObjectNotFoundException;
}
