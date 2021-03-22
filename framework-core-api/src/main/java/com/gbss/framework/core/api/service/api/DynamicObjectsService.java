package com.gbss.framework.core.api.service.api;

import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.DynamicObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
public interface DynamicObjectsService {

    DynamicObject createDynamicObject(String json);

    DynamicObject updateDynamicObject(String json);

    List<DynamicObject> getDynamicObjects(String objectTypeId);

    List<DynamicObject> getDynamicObjectsByParentId(String objectTypeId, String parentId);

    <T extends Base> Map<String, Object> getDynamicParameters(String json, Class<T> clazz);
}
