package com.gbss.framework.core.impl.controller;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.AttributeGroup;
import com.gbss.framework.core.model.entities.AttributeValue;
import com.gbss.framework.core.model.entities.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@RestController
@RequestMapping("/application/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AttributeSchemaController {

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @GetMapping("/5ea86babc8ae3bed0b307a4d/load/all")
    public List<ObjectType> getObjectTypes() {
        return attributeSchemaService.getObjectTypes();
    }

    @GetMapping(value="/5ea86babc8ae3bed0b307a4d/load/by/parent/{parentId}")
    public List<ObjectType> getObjectTypeByParentId(@PathVariable("parentId") String parentId) {
        return attributeSchemaService.getObjectTypeByParentId(parentId);
    }

    @GetMapping(value="/5ea86babc8ae3bed0b307a4d/load/{id}")
    public ObjectType getObjectTypeById(@PathVariable("id") String id) {
        return attributeSchemaService.getObjectTypeById(id);
    }

    @PostMapping("/5ea86babc8ae3bed0b307a4d/add")
    public ObjectType createObjectType(@RequestBody ObjectType objectType) {
        return attributeSchemaService.createObjectType(objectType);
    }

    @PutMapping(value="/5ea86babc8ae3bed0b307a4d/update")
    public ObjectType updateObjectType(@Valid @RequestBody ObjectType objectType) {
        return attributeSchemaService.updateObjectType(objectType);
    }

    @DeleteMapping(value="/5ea86babc8ae3bed0b307a4d/delete/{id}")
    public boolean deleteObjectType(@PathVariable("id") String id) {
        return attributeSchemaService.deleteObjectType(id);
    }

    @GetMapping("/5e934da667ed1fb0bcf0fca8/load/all")
    public List<AttributeGroup> getAttributeGroups() {
        return attributeSchemaService.getAttributeGroups();
    }

    @GetMapping(value="/5e934da667ed1fb0bcf0fca8/load/by/parent/{parentId}")
    public List<AttributeGroup> getAttributeGroupByParentId(@PathVariable("parentId") String parentId) {
        return attributeSchemaService.getAttributeGroupByParentId(parentId);
    }

    @GetMapping(value="/5e934da667ed1fb0bcf0fca8/load/{id}")
    public AttributeGroup getAttributeGroupById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeGroupById(id);
    }

    @PostMapping("/5e934da667ed1fb0bcf0fca8/add")
    public AttributeGroup createAttributeGroup(@Valid @RequestBody AttributeGroup attributeGroup) {
        return attributeSchemaService.createAttributeGroup(attributeGroup);
    }

    @PutMapping(value="/5e934da667ed1fb0bcf0fca8/update")
    public AttributeGroup updateAttributeGroup(@Valid @RequestBody AttributeGroup attributeGroup) {
        return attributeSchemaService.updateAttributeGroup(attributeGroup);
    }

    @DeleteMapping(value="/5e934da667ed1fb0bcf0fca8/delete/{id}")
    public boolean deleteAttributeGroup(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttributeGroup(id);
    }

    @GetMapping("/5e934d4567ed1fb0bcf0fca7/load/all")
    public List<Attribute> getAttributes() {
        return attributeSchemaService.getAttributes();
    }

    @GetMapping(value="/5e934d4567ed1fb0bcf0fca7/load/by/parent/{parentId}")
    public List<Attribute> getAttributeByParentId(@PathVariable("parentId") String parentId) {
        return attributeSchemaService.getAttributeByParentId(parentId);
    }

    @GetMapping(value="/5e934d4567ed1fb0bcf0fca7/load/{id}")
    public Attribute getAttributeById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeById(id);
    }

    @PostMapping("/5e934d4567ed1fb0bcf0fca7/add")
    public Attribute createAttribute(@Valid @RequestBody String json) {
        return attributeSchemaService.createAttribute(json);
    }

    @PutMapping(value="/5e934d4567ed1fb0bcf0fca7/update")
    public Attribute updateAttribute(@Valid @RequestBody String json) {
        return attributeSchemaService.updateAttribute(json);
    }

    @DeleteMapping(value="/5e934d4567ed1fb0bcf0fca7/delete/{id}")
    public boolean deleteAttribute(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttribute(id);
    }

    @GetMapping("/5ea6c35f3fe39bd27a715a33/load/all")
    public List<AttributeValue> getAttributeValues() {
        return attributeSchemaService.getAttributeValues();
    }

    @GetMapping(value="/5ea6c35f3fe39bd27a715a33/load/by/parent/{parentId}")
    public List<AttributeValue> getAttributeValuesByParentId(@PathVariable("parentId") String parentId) {
        return attributeSchemaService.getAttributeValuesByParentId(parentId);
    }

    @PostMapping("/5ea6c35f3fe39bd27a715a33/add")
    public AttributeValue createAttributeValue(@Valid @RequestBody AttributeValue attributeValue) {
        return attributeSchemaService.createAttributeValue(attributeValue);
    }

    @GetMapping(value="/5ea6c35f3fe39bd27a715a33/load/{id}")
    public AttributeValue getAttributeValueById(@PathVariable("id") String id) {
        return attributeSchemaService.getAttributeValueById(id);
    }

    @PutMapping(value="/5ea6c35f3fe39bd27a715a33/update")
    public AttributeValue updateAttributeValue(@Valid @RequestBody AttributeValue attributeValue) {
        return attributeSchemaService.updateAttributeValue(attributeValue);
    }

    @DeleteMapping(value="/5ea6c35f3fe39bd27a715a33/delete/{id}")
    public boolean deleteAttributeValue(@PathVariable("id") String id) {
        return attributeSchemaService.deleteAttributeValue(id);
    }
}


//http://localhost:8765/gbss-framework-core-service/application/api/5e934d4567ed1fb0bcf0fca7/update/all?attribute=parentId&value=603aad6fe1d7a507c3ba595c