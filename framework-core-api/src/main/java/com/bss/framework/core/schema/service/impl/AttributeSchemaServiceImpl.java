package com.bss.framework.core.schema.service.impl;

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
public class AttributeSchemaServiceImpl implements AttributeSchemaService {

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
    public ObjectType createObjectType(ObjectType objectType) {
        System.out.println("... createObjectType, objectType: "+objectType);
        return objectTypeRepository.save(objectType);
    }

    @Override
    public ObjectType getObjectTypeById(String id) {
        return objectTypeRepository.findById(id).get();
    }

    @Override
    public ObjectType updateObjectType(ObjectType objectType) {
        System.out.println("... updateObjectType, objectType: "+objectType);
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
    public AttributeGroup createAttributeGroup(AttributeGroup attributeGroup) {
        System.out.println("... createAttributeGroup, attributeGroup: "+attributeGroup);
        return attributeGroupRepository.save(attributeGroup);
    }

    @Override
    public AttributeGroup getAttributeGroupById(String id) {
        return attributeGroupRepository.findById(id).get();
    }

    @Override
    public AttributeGroup updateAttributeGroup(AttributeGroup attributeGroup) {
        System.out.println("... updateAttributeGroup, attributeGroup: "+attributeGroup);
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
    public Attribute createAttribute(Attribute attribute) {
        System.out.println("... createAttribute, attribute: "+attribute);
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute getAttributeById(String id) {
        return attributeRepository.findById(id).get();
    }

    @Override
    public Attribute updateAttribute(Attribute attribute) {
        System.out.println("... updateAttribute, attribute: "+attribute);
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
    public AttributeValue createAttributeValue(AttributeValue attributeValue) {
        System.out.println("... createAttributeValue, attributeValue: "+attributeValue);
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public AttributeValue getAttributeValueById(String id) {
        return attributeValueRepository.findById(id).get();
    }

    @Override
    public AttributeValue updateAttributeValue(AttributeValue attributeValue) {
        System.out.println("... updateAttributeValue, attributeValue: "+attributeValue);
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
