package com.gbss.framework.core.web.api.converters;


import com.gbss.framework.core.model.entities.Base;

import java.util.List;

public interface Converter<T, X extends Base> {

    T convert(X input);

    List<T> convert(List<X> input);

}
