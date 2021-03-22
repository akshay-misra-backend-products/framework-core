package com.gbss.framework.core.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.BooleanAttr;
import com.gbss.framework.core.meta.annotations.RefAttr;
import com.gbss.framework.core.meta.annotations.RefIdAttr;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributes")
public class Attribute extends Base {

    @DBRef
    @NotNull
    @RefAttr
    @JsonProperty(SystemConstants.Attributes.ATTRIBUTE_GROUP)
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_GROUP)
    private AttributeGroup attributeGroup;

    @NotNull
    @JsonProperty(SystemConstants.Attributes.ATTRIBUTE_TYPE)
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_TYPE)
    private int attributeType;

    @RefIdAttr
    @JsonProperty(SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPE)
    @AttributeId(SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPE)
    private String referenceToObjectType;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.IS_SYSTEM)
    @AttributeId(SystemConstants.Attributes.IS_SYSTEM)
    private boolean system;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.READONLY)
    @AttributeId(SystemConstants.Attributes.READONLY)
    private boolean readonly;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.REQUIRED)
    @AttributeId(SystemConstants.Attributes.REQUIRED)
    private boolean required;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.HIDDEN)
    @AttributeId(SystemConstants.Attributes.HIDDEN)
    private boolean hidden;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.MULTIPLE)
    @AttributeId(SystemConstants.Attributes.MULTIPLE)
    private boolean multiple;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.SHOW_IN_CREATE)
    @AttributeId(SystemConstants.Attributes.SHOW_IN_CREATE)
    private boolean showInCreate;

    @AttributeId(SystemConstants.Attributes.DYNAMIC_PARAMETERS)
    private Map<String, Object> moduleParameters = new HashMap<>();

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public int getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(int attributeType) {
        this.attributeType = attributeType;
    }

    public String getReferenceToObjectType() {
        return referenceToObjectType;
    }

    public void setReferenceToObjectType(String referenceToObjectType) {
        this.referenceToObjectType = referenceToObjectType;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isShowInCreate() {
        return showInCreate;
    }

    public void setShowInCreate(boolean showInCreate) {
        this.showInCreate = showInCreate;
    }

    public Map<String, Object> getModuleParameters() {
        return moduleParameters;
    }

    public void setModuleParameters(Map<String, Object> moduleParameters) {
        this.moduleParameters = moduleParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return this.getId() == attribute.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
