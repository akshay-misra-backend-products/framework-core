package com.gbss.framework.core.api.json.builder;

import com.gbss.framework.core.model.entities.DynamicObject;

public interface JsonToDynamicObjectBuilder {

    DynamicObject build(String json);
}
