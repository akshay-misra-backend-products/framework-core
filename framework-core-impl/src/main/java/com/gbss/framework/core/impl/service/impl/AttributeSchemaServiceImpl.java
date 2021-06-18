package com.gbss.framework.core.impl.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.service.api.DynamicObjectsService;
import com.gbss.framework.core.impl.factory.ObjectTypeRepositoryMapperFactory;
import com.gbss.framework.core.impl.meta.data.MetadataHelper;
import com.gbss.framework.core.impl.repositories.AttributeGroupRepository;
import com.gbss.framework.core.impl.repositories.AttributeRepository;
import com.gbss.framework.core.impl.repositories.AttributeValueRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.impl.utils.CommonUtils;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.*;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    DynamicObjectsService dynamicObjectsService;

    @Autowired
    MetadataHelper metadataHelper;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    ObjectTypeRepositoryMapperFactory objectTypeRepositoryFactory;

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
        System.out.println("... ********** ########## getObjectTypeById, id: "+ id);
        Optional<ObjectType> objectTypeOp = objectTypeRepository.findById(id);
        return objectTypeOp.isPresent() ? objectTypeOp.get() : null;
    }

    @Override
    public ObjectType createObjectType(ObjectType objectType) {
        System.out.println("... createObjectType, objectType: "+objectType);
        objectType.setObjectTypeId(SystemConstants.ObjectTypes.OBJECT_TYPE);
        ObjectType objectTypeFromDB = objectTypeRepository.save(objectType);

        objectTypeFromDB.setLoadAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/load/all"));
        objectTypeFromDB.setLoadByIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId() + "/load/"));
        objectTypeFromDB.setLoadDetailsByIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/load/details/" + objectTypeFromDB.getId()));
        objectTypeFromDB.setLoadByParentIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/" + objectTypeFromDB.getId() + "/load/by/parent/"));
        objectTypeFromDB.setAddAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/add"));
        objectTypeFromDB.setUpdateAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/update"));
        objectTypeFromDB.setDeleteAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/delete/:id"));
        return objectTypeRepository.save(objectTypeFromDB);
    }

    @Override
    public ObjectType updateObjectType(ObjectType objectType) {
        System.out.println("... updateObjectType, objectType: "+objectType);
        Optional<ObjectType> objectTypeOp = objectTypeRepository.findById(objectType.getId());
        ObjectType objectTypeFromDB = objectTypeOp.get();
        objectType.setLoadAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/load/all"));
        objectType.setLoadByIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/load/"));
        objectType.setLoadDetailsByIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/load/details/"+objectTypeFromDB.getId()));
        objectType.setLoadByParentIdAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/" + objectTypeFromDB.getId() + "/load/by/parent/"));
        objectType.setAddAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/add"));
        objectType.setUpdateAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/update"));
        objectType.setDeleteAPI(commonUtils.constructApiUrl(objectType,
                "/application/api/"+objectTypeFromDB.getId()+"/delete/:id"));
        handleAudit(objectTypeFromDB, objectType);
        return objectTypeRepository.save(objectType);
    }

    @Override
    public boolean updateAllObjectType() {
        List<ObjectType> objectTypes = objectTypeRepository.findAll();
        //objectTypes.stream().forEach(objectType -> updateObjectType(objectType));

        //updating collection names for framework core object types
        /*objectTypes.stream().forEach(objectType -> {
            if (objectType.getCollectionName() == null) {
                MongoRepository repository = objectTypeRepositoryFactory.getBean(objectType.getId());
                if (repository != null) {
                    Base base = (Base) repository.findAll().stream().findFirst().orElse(null);
                    System.out.println("****** ###### Document:" + base.getClass().getAnnotation(Document.class));
                    System.out.println("****** ###### Document.value" + base.getClass().getAnnotation(Document.class).collection());
                    if (base.getClass().getAnnotation(Document.class) != null
                    && base.getClass().getAnnotation(Document.class).collection() != null) {
                        String collection = base.getClass().getAnnotation(Document.class).collection();
                        objectType.setCollectionName(collection);
                        objectTypeRepository.save(objectType);
                    }
                }
            }
        });*/

        return true;
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
    public boolean updateAllAttributeGroup(String attribute, String value) {
        System.out.println("updateAllAttributeGroup, attribute: " + attribute + ", value: " + value);
        List<AttributeGroup> attributeGroups = attributeGroupRepository.findAll();
        attributeGroups.stream().forEach(attr -> {
                    Field field = metadataHelper.getField(attr.getClass(), attribute);
                    metadataHelper.setValue(attr, attr.getClass(), field, value);
                    attributeGroupRepository.save(attr);
                }
        );

        return true;
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
    public Attribute createAttribute(String json) {
        JsonMapper mapper = new JsonMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Attribute attribute = null;
        try {
            attribute = mapper.readValue(json, Attribute.class);
            attribute.getModuleParameters().putAll(dynamicObjectsService.getDynamicParameters(json, Attribute.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Exception caught while creating Attribute: " + e);
        }

        System.out.println("... createAttribute, attribute: " + attribute);
        attribute.setObjectTypeId(SystemConstants.ObjectTypes.ATTRIBUTE);
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute updateAttribute(String json) {
        System.out.println("... updateAttribute, json: " + json);
        JsonMapper mapper = new JsonMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Attribute attribute = null;
        try {
            attribute = mapper.readValue(json, Attribute.class);
            attribute.getModuleParameters().putAll(dynamicObjectsService.getDynamicParameters(json, Attribute.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Exception caught while updating Attribute: " + e);
        }

        System.out.println("... createAttribute, attribute: " + attribute);
        Optional<Attribute> attributeOp = attributeRepository.findById(attribute.getId());
        handleAudit(attributeOp.get(), attribute);
        return attributeRepository.save(attribute);
    }

    @Override
    public boolean updateAllAttribute(String attribute, String value) {
        System.out.println("updateAllAttribute, attribute: " + attribute + ", value: " + value);
        /*List<Attribute> attributes = attributeRepository.findAllBySystemTrue();*/
        List<Attribute> attributes = attributeRepository.findAll();
        attributes.stream().forEach(attr -> {
            String key = CaseUtils.toCamelCase(attr.getName(), false);
            System.out.println("----------  ****** updateAllAttribute, key: " + key);
            attr.setKey(key);
            attributeRepository.save(attr);
            /*Field field = metadataHelper.getField(attr.getClass(), attribute);
                metadataHelper.setValue(attr, attr.getClass(), field, value);
                attributeRepository.save(attr);*/
            }
        );

        return true;
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
}
