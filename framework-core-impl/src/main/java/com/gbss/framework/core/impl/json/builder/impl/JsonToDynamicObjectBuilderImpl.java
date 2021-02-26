package com.gbss.framework.core.impl.json.builder.impl;

import com.gbss.framework.core.api.json.builder.JsonToDynamicObjectBuilder;
import com.gbss.framework.core.impl.meta.data.MetadataHelper;
import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.model.constants.AttributeType;
import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.DynamicObject;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class JsonToDynamicObjectBuilderImpl implements JsonToDynamicObjectBuilder {

    @Autowired
    MetadataHelper metadataHelper;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Override
    public DynamicObject build(String json) {
        System.out.println("*****  &&&&&&  %%%%%  $$$$$   #######  createObject, json: " + json);
        JSONObject jsonObject = new JSONObject(json);
        DynamicObject dynamicObject = new DynamicObject();
        jsonObject.keySet().stream()
                .filter(key -> !"createdAt".equals(key) && !"lastModifiedAt".equals(key))
                .forEach(key -> {
            System.out.println("*****  &&&&&&  %%%%%  $$$$$   ####### build, key: " + key);
            Field field =  metadataHelper.getField(dynamicObject.getClass(), key);
            if (field == null) {
                field =  metadataHelper.getField(dynamicObject.getClass().getSuperclass(), key);
            }
            System.out.println("*****  &&&&&&  %%%%%  $$$$$   ####### build, field: " + field);
            if (field != null) {
                Object value = null;
                if (jsonObject.get(key) != null) {
                    if (Date.class.equals(field.getType())) {
                        try {
                            value = DateFormat.getInstance().parse(jsonObject.getString(key));
                        } catch (ParseException e) {
                            System.out.println("*****  &&&&&&  %%%%%  $$$$$   ####### build, ParseException: " + e);
                        }
                    } else if (Long.class.equals(field.getType())) {
                        value = jsonObject.getLong(key);
                    } else {
                        value = jsonObject.get(key);
                    }
                    metadataHelper.setValue(dynamicObject, Base.class, field, value);
                }
            } else {
                dynamicObject.getExtendedParameters().putAll(getValueToPersist(key, jsonObject));
            }
        });

        System.out.println("*****  &&&&&&  %%%%%  $$$$$   ####### build, dynamicObject: " + dynamicObject);
        return dynamicObject;
    }

    public Map<String, Object> getValueToPersist(String attributeId, JSONObject jsonObject) {
        System.out.println("********** JsonToDynamicObjectBuilderImpl getValueToPersist, attributeId: "+ attributeId);
        Map<String, Object> extension = new HashMap<>();
        Attribute attribute = attributeSchemaService.getAttributeById(attributeId);
        System.out.println("*****  &&&&&& getValueToPersist, attribute: " + attribute);
        if (AttributeType.REFERENCE.value == attribute.getAttributeType()) {
            System.out.println("*****  &&&&&& getValueToPersist, ReferenceToObjectType: " +
                    attribute.getReferenceToObjectType());
            if (attribute.getReferenceToObjectType() != null) {
                if (attribute.isMultiple()) {
                    System.out.println("*****  &&&&&& getValueToPersist, isMultiple reference: " + true);
                    JSONArray array = jsonObject.getJSONArray(attributeId);
                    List<String> values = new ArrayList<>(array.length());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject reference = array.getJSONObject(i);
                        System.out.println("*****  &&&&&& getValueToPersist, reference: " + reference);
                        String id = (String) reference.get("id");
                        System.out.println("*****  &&&&&& getValueToPersist, reference id: " + id);
                        values.add(id);
                    }
                    System.out.println("*****  &&&&&& getValueToPersist, values: " + values);
                    extension.put(attributeId, values);
                } else {
                    JSONObject reference = jsonObject.getJSONObject(attributeId);
                    String id = (String) reference.get("id");
                    System.out.println("*****  &&&&&& getValueToPersist, reference id: " + id);
                    extension.put(attributeId, id);
                }
            }
        } else {
            System.out.println("*****  &&&&&& getValueToPersist, non reference value: " + jsonObject.get(attributeId));
            extension.put(attributeId, jsonObject.get(attributeId));
        }

        System.out.println("*****  &&&&&& getValueToPersist, extension: " + extension);

        return extension;
    }
}
