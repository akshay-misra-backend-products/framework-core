package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributeValues")
public class AttributeValue extends Base {

    private String attributeId;

    /*
    * Propagate from attribute, hide in UI.
    */
    @NotNull
    private int attributeType;

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }
}
