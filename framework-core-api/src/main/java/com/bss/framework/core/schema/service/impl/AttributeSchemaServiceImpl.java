package com.bss.framework.core.schema.service.impl;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.model.Attribute;
import com.bss.framework.core.schema.model.AttributeGroup;
import com.bss.framework.core.schema.model.AttributeValue;
import com.bss.framework.core.schema.model.ObjectType;
import com.bss.framework.core.schema.repositories.AttributeGroupRepository;
import com.bss.framework.core.schema.repositories.AttributeRepository;
import com.bss.framework.core.schema.repositories.AttributeValueRepository;
import com.bss.framework.core.schema.repositories.ObjectTypeRepository;
import com.bss.framework.core.schema.service.api.AttributeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    @Override
    public List<ObjectType> getObjectTypes() {
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
        return objectTypeRepository.findAll(sortByName);
    }

    @Override
    public List<ObjectType> getObjectTypeByParentId(String parentId) {
        return objectTypeRepository.findByParentId(parentId);
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
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
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
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
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
        Sort sortByName = new Sort(Sort.Direction.DESC, "name");
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
