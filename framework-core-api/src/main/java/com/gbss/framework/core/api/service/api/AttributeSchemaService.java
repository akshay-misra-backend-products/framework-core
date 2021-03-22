package com.gbss.framework.core.api.service.api;

import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.AttributeGroup;
import com.gbss.framework.core.model.entities.AttributeValue;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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

    boolean updateAllObjectType();

    boolean deleteObjectType(String id);

    List<AttributeGroup> getAttributeGroups();

    AttributeGroup createAttributeGroup(AttributeGroup objectType);

    AttributeGroup getAttributeGroupById(String id);

    List<AttributeGroup> getAttributeGroupByParentId(String parentId);

    AttributeGroup updateAttributeGroup(AttributeGroup objectType);

    boolean updateAllAttributeGroup(String attribute, String value);

    boolean deleteAttributeGroup(String id);

    List<Attribute> getAttributes();

    Attribute createAttribute(String json);

    Attribute getAttributeById(String id);

    List<Attribute> getAttributeByParentId(String parentId);

    Attribute updateAttribute(String json);

    boolean updateAllAttribute(String attribute, String value);

    boolean deleteAttribute(String id);

    List<AttributeValue> getAttributeValues();

    List<AttributeValue> getAttributeValuesByParentId(String parentId);

    AttributeValue createAttributeValue(AttributeValue attributeValue);

    AttributeValue getAttributeValueById(String id);

    AttributeValue updateAttributeValue(AttributeValue attributeValue);

    boolean deleteAttributeValue(String id);
}
