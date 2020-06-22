package com.gbss.framework.core.api.service.api;

import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.AttributeGroup;
import com.gbss.framework.core.model.entities.AttributeValue;
import com.gbss.framework.core.model.entities.DynamicObject;
import com.gbss.framework.core.model.entities.ObjectType;

import java.util.List;

/**
 * Created by Akshay Misra on 16-06-2019.
 */
public interface AttributeSchemaService {

    List<ObjectType> getObjectTypes();

    ObjectType getObjectTypeById(String id);

    List<ObjectType> getObjectTypeByParentId(String parentId);

    ObjectType createObjectType(ObjectType objectType);

    ObjectType updateObjectType(ObjectType objectType);

    boolean deleteObjectType(String id);

    List<AttributeGroup> getAttributeGroups();

    AttributeGroup createAttributeGroup(AttributeGroup objectType);

    AttributeGroup getAttributeGroupById(String id);

    List<AttributeGroup> getAttributeGroupByParentId(String parentId);

    AttributeGroup updateAttributeGroup(AttributeGroup objectType);

    boolean deleteAttributeGroup(String id);

    List<Attribute> getAttributes();

    Attribute createAttribute(Attribute objectType);

    Attribute getAttributeById(String id);

    List<Attribute> getAttributeByParentId(String parentId);

    Attribute updateAttribute(Attribute objectType);

    boolean deleteAttribute(String id);

    List<AttributeValue> getAttributeValues();

    List<AttributeValue> getAttributeValuesByParentId(String parentId);

    AttributeValue createAttributeValue(AttributeValue attributeValue);

    AttributeValue getAttributeValueById(String id);

    AttributeValue updateAttributeValue(AttributeValue attributeValue);

    boolean deleteAttributeValue(String id);

    DynamicObject createDynamicObject(String json);

    List<DynamicObject> getDynamicObjects(String objectTypeId);
}
