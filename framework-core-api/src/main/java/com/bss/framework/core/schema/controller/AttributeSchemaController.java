package com.bss.framework.core.schema.controller;

import com.bss.framework.core.schema.model.Attribute;
import com.bss.framework.core.schema.model.AttributeGroup;
import com.bss.framework.core.schema.model.AttributeValue;
import com.bss.framework.core.schema.model.ObjectType;
import com.bss.framework.core.schema.service.api.AttributeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rocky on 15-06-2019.
 */
@RestController
@RequestMapping("/application/schema")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AttributeSchemaController {

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @GetMapping("/get/object-types")
    public List<ObjectType> getObjectTypes() {
        return attributeSchemaService.getObjectTypes();
    }

    @PostMapping("/add/object-type")
    public ObjectType createObjectType(@Valid @RequestBody ObjectType objectType) {
        return attributeSchemaService.createObjectType(objectType);
    }

    @GetMapping(value="/get/object-type/{id}")
    public ObjectType getObjectTypeById(@PathVariable("id") String id) {
        return attributeSchemaService.getObjectTypeById(id);
    }

    @PutMapping(value="/update/object-type")
    public ObjectType updateObjectType(@Valid @RequestBody ObjectType objectType) {
        return attributeSchemaService.updateObjectType(objectType);
    }

    @DeleteMapping(value="/delete/object-type/{id}")
    public boolean deleteObjectType(@PathVariable("id") String id) {
        return attributeSchemaService.deleteObjectType(id);
    }

    @GetMapping("/get/attribute-groups")
    public List<AttributeGroup> getAttributeGroups() {
        return attributeSchemaService.getAttributeGroups();
    }

    @PostMapping("/add/attribute-group")
    public AttributeGroup createAttributeGroup(@Valid @RequestBody AttributeGroup attributeGroup) {
        return attributeSchemaService.createAttributeGroup(attributeGroup);
    }

    @GetMapping(value="/get/attribute-group/{id}")
    public AttributeGroup getAttributeGroupById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeGroupById(id);
    }

    @PutMapping(value="/update/attribute-group")
    public AttributeGroup updateAttributeGroup(@Valid @RequestBody AttributeGroup attributeGroup) {
        return attributeSchemaService.updateAttributeGroup(attributeGroup);
    }

    @DeleteMapping(value="/delete/attribute-group/{id}")
    public boolean deleteAttributeGroup(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttributeGroup(id);
    }

    @GetMapping("/get/attributes")
    public List<Attribute> getAttributes() {
        return attributeSchemaService.getAttributes();
    }

    @PostMapping("/add/attribute")
    public Attribute createAttribute(@Valid @RequestBody Attribute attribute) {
        return attributeSchemaService.createAttribute(attribute);
    }

    @GetMapping(value="/get/attribute/{id}")
    public Attribute getAttributeById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeById(id);
    }

    @PutMapping(value="/update/attribute")
    public Attribute updateAttribute(@Valid @RequestBody Attribute attribute) {
        return attributeSchemaService.updateAttribute(attribute);
    }

    @DeleteMapping(value="/delete/attribute/{id}")
    public boolean deleteAttribute(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttribute(id);
    }

    @GetMapping("/get/attribute-values")
    public List<AttributeValue> getAttributeValues() {
        return attributeSchemaService.getAttributeValues();
    }

    @PostMapping("/add/attribute-value")
    public AttributeValue createAttributeValue(@Valid @RequestBody AttributeValue attributeValue) {
        return attributeSchemaService.createAttributeValue(attributeValue);
    }

    @GetMapping(value="/get/attribute-value/{id}")
    public AttributeValue getAttributeValueById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeValueById(id);
    }

    @PutMapping(value="/update/attribute-value")
    public AttributeValue updateAttributeValue(@Valid @RequestBody AttributeValue attributeValue) {
        return attributeSchemaService.updateAttributeValue(attributeValue);
    }

    @DeleteMapping(value="/delete/attribute-value/{id}")
    public boolean deleteAttributeValue(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttributeValue(id);
    }
}
