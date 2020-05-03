package com.bss.framework.core.schema.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributeValues")
public class AttributeValue extends Base {

    @NotBlank
    @JsonProperty(SystemConstants.Attributes.VALUE)
    @AttributeId(SystemConstants.Attributes.VALUE)
    private String value;

    /*
    * Propagate from attribute, hide in UI.
    */
    @NotNull
    @JsonProperty(SystemConstants.Attributes.ATTRIBUTE_TYPE)
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_TYPE)
    private int attributeType;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }
}
