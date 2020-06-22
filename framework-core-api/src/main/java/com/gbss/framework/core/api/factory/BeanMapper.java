package com.gbss.framework.core.api.factory;

public interface BeanMapper<T> {

    T getBean(String key);
}
