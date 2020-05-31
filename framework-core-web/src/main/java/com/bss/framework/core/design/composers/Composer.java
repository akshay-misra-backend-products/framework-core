package com.bss.framework.core.design.composers;

import javax.ejb.ObjectNotFoundException;

public interface Composer<T> {

    T compose(String objectTypeId, String objectId) throws ObjectNotFoundException;
}
