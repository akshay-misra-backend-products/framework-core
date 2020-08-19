package com.gbss.framework.core.impl.service.impl;

import com.gbss.framework.core.impl.json.builder.impl.JsonToDynamicObjectBuilderImpl;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.impl.repositories.AttributeGroupRepository;
import com.gbss.framework.core.impl.repositories.AttributeRepository;
import com.gbss.framework.core.impl.repositories.AttributeValueRepository;
import com.gbss.framework.core.impl.repositories.DynamicObjectRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.AttributeGroup;
import com.gbss.framework.core.model.entities.AttributeValue;
import com.gbss.framework.core.model.entities.DynamicObject;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akshay Misra on 16-06-2019.
 */
@Service
public class AttributeSchemaServiceImpl extends ApplicationAuditServiceImpl implements AttributeSchemaService {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    AttributeGroupRepository attributeGroupRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    DynamicObjectRepository dynamicObjectRepository;

    @Autowired
    JsonToDynamicObjectBuilderImpl jsonToDynamicObjectBuilder;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<ObjectType> getObjectTypes() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return objectTypeRepository.findAll(sortByName);
    }

    @Override
    public List<ObjectType> getObjectTypeByParentId(String parentId) {
        System.out.println("... getObjectTypeByParentId, parentId: "+ parentId);
        if ("-100".equals(parentId)) {
            System.out.println("... getObjectTypeByParentId, parentId is null string........ ");
            parentId = null;
        }
        List<ObjectType> objectTypes = objectTypeRepository.findByParentId(parentId);
        System.out.println("... ********** getObjectTypeByParentId, objectTypes: "+ objectTypes);
        return objectTypes;
    }

    @Override
    public ObjectType getObjectTypeById(String id) {
        return objectTypeRepository.findById(id).get();
    }

    @Override
    public ObjectType createObjectType(ObjectType objectType) {
        System.out.println("... createObjectType, objectType: "+objectType);
        objectType.setObjectTypeId(SystemConstants.ObjectTypes.OBJECT_TYPE);
        ObjectType objectTypeFromDB = objectTypeRepository.save(objectType);
        objectTypeFromDB.setLoadAPI("/application/api/"+objectTypeFromDB.getId()+"/load/all");
        objectTypeFromDB.setLoadByIdAPI("/application/api/"+objectTypeFromDB.getId()+"/load/:id");
        objectTypeFromDB.setAddAPI("/application/api/"+objectTypeFromDB.getId()+"/add");
        objectTypeFromDB.setUpdateAPI("/application/api/"+objectTypeFromDB.getId()+"/update");
        objectTypeFromDB.setDeleteAPI("/application/api/"+objectTypeFromDB.getId()+"/delete/:id");
        return objectTypeRepository.save(objectTypeFromDB);
    }

    @Override
    public ObjectType updateObjectType(ObjectType objectType) {
        System.out.println("... updateObjectType, objectType: "+objectType);
        Optional<ObjectType> objectTypeOp = objectTypeRepository.findById(objectType.getId());
        ObjectType objectTypeFromDB = objectTypeOp.get();
        objectType.setLoadAPI("/application/api/"+objectTypeFromDB.getId()+"/load/all");
        objectType.setLoadByIdAPI("/application/api/"+objectTypeFromDB.getId()+"/load/:id");
        objectType.setAddAPI("/application/api/"+objectTypeFromDB.getId()+"/add");
        objectType.setUpdateAPI("/application/api/"+objectTypeFromDB.getId()+"/update");
        objectType.setDeleteAPI("/application/api/"+objectTypeFromDB.getId()+"/delete/:id");
        handleAudit(objectTypeOp.get(), objectType);
        return objectTypeRepository.save(objectType);
    }

    @Override
    public boolean deleteObjectType(String id) {
        System.out.println("... deleteObjectType, id: "+id);
        Optional<ObjectType> objectType = objectTypeRepository.findById(id);
        if (objectType != null) {
            objectTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AttributeGroup> getAttributeGroups() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return attributeGroupRepository.findAll(sortByName);
    }

    @Override
    public List<AttributeGroup> getAttributeGroupByParentId(String parentId) {
        return attributeGroupRepository.findByParentId(parentId);
    }

    @Override
    public AttributeGroup getAttributeGroupById(String id) {
        return attributeGroupRepository.findById(id).get();
    }

    @Override
    public AttributeGroup createAttributeGroup(AttributeGroup attributeGroup) {
        System.out.println("... createAttributeGroup, attributeGroup: "+attributeGroup);
        attributeGroup.setObjectTypeId(SystemConstants.ObjectTypes.ATTRIBUTE_GROUP);
        return attributeGroupRepository.save(attributeGroup);
    }

    @Override
    public AttributeGroup updateAttributeGroup(AttributeGroup attributeGroup) {
        System.out.println("... updateAttributeGroup, attributeGroup: "+attributeGroup);
        Optional<AttributeGroup> attributeGroupOp = attributeGroupRepository.findById(attributeGroup.getId());
        handleAudit(attributeGroupOp.get(), attributeGroup);
        return attributeGroupRepository.save(attributeGroup);
    }

    @Override
    public boolean deleteAttributeGroup(String id) {
        System.out.println("... deleteAttributeGroup, id: "+id);
        Optional<AttributeGroup> attributeGroup = attributeGroupRepository.findById(id);
        if (attributeGroup != null) {
            attributeGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Attribute> getAttributes() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return attributeRepository.findAll(sortByName);
    }

    @Override
    public List<Attribute> getAttributeByParentId(String parentId) {
        return attributeRepository.findByParentId(parentId);
    }

    @Override
    public Attribute getAttributeById(String id) {
        return attributeRepository.findById(id).get();
    }

    @Override
    public Attribute createAttribute(Attribute attribute) {
        System.out.println("... createAttribute, attribute: "+attribute);
        attribute.setObjectTypeId(SystemConstants.ObjectTypes.ATTRIBUTE);
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute updateAttribute(Attribute attribute) {
        System.out.println("... updateAttribute, attribute: "+attribute);
        Optional<Attribute> attributeOp = attributeRepository.findById(attribute.getId());
        handleAudit(attributeOp.get(), attribute);
        return attributeRepository.save(attribute);
    }

    @Override
    public boolean deleteAttribute(String id) {
        System.out.println("... deleteAttribute, id: "+id);
        Optional<Attribute> attribute = attributeRepository.findById(id);
        if (attribute != null) {
            attributeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AttributeValue> getAttributeValues() {
        Sort sortByName = Sort.by(Sort.Direction.DESC, "name");
        return attributeValueRepository.findAll(sortByName);
    }

    @Override
    public List<AttributeValue> getAttributeValuesByParentId(String parentId) {
        return attributeValueRepository.findByParentId(parentId);
    }

    @Override
    public AttributeValue createAttributeValue(AttributeValue attributeValue) {
        System.out.println("... createAttributeValue, attributeValue: "+attributeValue);
        Attribute attribute = getAttributeById(attributeValue.getParentId());
        attributeValue.setAttributeType(attribute.getAttributeType());
        attributeValue.setObjectTypeId(SystemConstants.ObjectTypes.ATTRIBUTE_VALUE);
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public AttributeValue getAttributeValueById(String id) {
        return attributeValueRepository.findById(id).get();
    }

    @Override
    public AttributeValue updateAttributeValue(AttributeValue attributeValue) {
        System.out.println("... updateAttributeValue, attributeValue: "+attributeValue);
        Optional<AttributeValue> attributeValueOp = attributeValueRepository.findById(attributeValue.getId());
        handleAudit(attributeValueOp.get(), attributeValue);
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public boolean deleteAttributeValue(String id) {
        System.out.println("... deleteAttributeValue, id: "+id);
        Optional<AttributeValue> attributeValue = attributeValueRepository.findById(id);
        if (attributeValue != null) {
            attributeValueRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public DynamicObject createDynamicObject(String json) {
        DynamicObject dynamicObject = jsonToDynamicObjectBuilder.build(json);
        ObjectType objectType = objectTypeRepository.findById(dynamicObject.getObjectTypeId()).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            if (!mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                mongoTemplate.createCollection(objectType.getCollectionName());
            }
            mongoTemplate.save(dynamicObject, objectType.getCollectionName());
        } else {
            dynamicObjectRepository.save(dynamicObject);
        }

        return dynamicObject;
    }

    @Override
    public DynamicObject updateDynamicObject(String json) {
        DynamicObject dynamicObject = jsonToDynamicObjectBuilder.build(json);
        System.out.println("******* updateDynamicObject, dynamicObject: " + dynamicObject);
        ObjectType objectType = objectTypeRepository.findById(dynamicObject.getObjectTypeId()).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            if (!mongoTemplate.getCollectionNames().contains(objectType.getCollectionName())) {
                mongoTemplate.createCollection(objectType.getCollectionName());
            }
            mongoTemplate.save(dynamicObject, objectType.getCollectionName());
        } else {
            dynamicObjectRepository.save(dynamicObject);
        }

        return dynamicObject;
    }

    @Override
    public List<DynamicObject> getDynamicObjects(String objectTypeId) {
        List<DynamicObject> objects = new ArrayList<>();
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            objects.addAll(mongoTemplate.findAll(DynamicObject.class, objectType.getCollectionName()));
        }

        return objects;
    }

    @Override
    public List<DynamicObject> getDynamicObjectsByParentId(String objectTypeId, String parentId) {
        List<DynamicObject> objects = new ArrayList<>();
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        if (!StringUtils.isEmpty(objectType.getCollectionName())) {
            objects.addAll(mongoTemplate.find(Query.query(Criteria.where("parentId").is(parentId)),
                    DynamicObject.class, objectType.getCollectionName()));
        }

        return objects;
    }
}
