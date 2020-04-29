package com.bss.framework.core.design.composers;

public interface Composer<T> {

    T compose(String objectTypeId, String objectId);
}
