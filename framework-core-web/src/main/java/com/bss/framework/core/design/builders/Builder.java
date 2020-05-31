package com.bss.framework.core.design.builders;

public interface Builder<T> {

    Builder createBuilder();

    T build();

}
