package com.gbss.framework.core.api.json.builder;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.DynamicObject;

import java.util.Map;

public interface JsonToDynamicObjectBuilder {

    DynamicObject build(String json);

    <T extends Base> Map<String, Object> getDynamicParameters(String json, Class<T> clazz);
}
