package com.bss.framework.core.schema.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Akshay Misra on 09-04-2020.
 */
@Document(collection="objectCharacteristics")
public class ObjectCharacteristic extends Base {

    @RefIdAttr
    @AttributeId(SystemConstants.Attributes.REF_TO_ATTRIBUTE)
    private String attributeId;

    @RefIdAttr
    @Hidden
    @AttributeId(SystemConstants.Attributes.REF_TO_OBJECT)
    private String objectId;

    @Hidden
    @AttributeId(SystemConstants.Attributes.TEXT_VALUE)
    private String textValue;

    @Hidden
    @AttributeId(SystemConstants.Attributes.NUMBER_VALUE)
    private Integer numberValue;

    @RefIdAttr
    @Hidden
    @AttributeId(SystemConstants.Attributes.REFERENCE_VALUE)
    private String referenceValue;

    @RefIdAttr
    @Hidden
    @AttributeId(SystemConstants.Attributes.MULTI_REFERENCE_VALUE)
    private List<String> multipleReferenceValue;

    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @Hidden
    @AttributeId(SystemConstants.Attributes.BOOLEAN_VALUE)
    private boolean booleanValue;

    @DBRef(lazy = true)
    @RefAttr
    @Hidden
    @AttributeId(SystemConstants.Attributes.LIST_VALUE)
    private AttributeValue listValue;

    @DBRef(lazy = true)
    @RefAttr
    @Hidden
    @AttributeId(SystemConstants.Attributes.MULTI_LIST_VALUE)
    private List<AttributeValue> multipleListValue;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public String getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(String referenceValue) {
        this.referenceValue = referenceValue;
    }

    public List<String> getMultipleReferenceValue() {
        return multipleReferenceValue;
    }

    public void setMultipleReferenceValue(List<String> multipleReferenceValue) {
        this.multipleReferenceValue = multipleReferenceValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public AttributeValue getListValue() {
        return listValue;
    }

    public void setListValue(AttributeValue listValue) {
        this.listValue = listValue;
    }

    public List<AttributeValue> getMultipleListValue() {
        return multipleListValue;
    }

    public void setMultipleListValue(List<AttributeValue> multipleListValue) {
        this.multipleListValue = multipleListValue;
    }
}
