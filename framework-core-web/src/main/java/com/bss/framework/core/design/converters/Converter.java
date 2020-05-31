package com.bss.framework.core.design.converters;

import com.bss.framework.core.schema.model.Base;

import java.util.List;

public interface Converter<T, X extends Base> {

    T convert(X input);

    List<T> convert(List<X> input);

}
