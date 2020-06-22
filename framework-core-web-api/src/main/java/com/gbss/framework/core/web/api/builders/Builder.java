package com.gbss.framework.core.web.api.builders;

public interface Builder<T> {

    Builder createBuilder();

    T build();

}
