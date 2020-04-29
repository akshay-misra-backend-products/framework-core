package com.bss.framework.core.schema.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.bss.framework.core.schema.meta.data.annotations.BooleanAttr;
import com.bss.framework.core.schema.meta.data.annotations.RefAttr;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributes")
public class Attribute extends Base {

    @DBRef
    @NotNull
    @RefAttr
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_GROUP)
    private AttributeGroup attributeGroup;

    @NotNull
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_TYPE)
    private int attributeType;

    @RefAttr
    @AttributeId(SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPE)
    private String referenceToObjectType;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.USAE_AS_FILTER)
    private boolean useAsFilter;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.FOR_CATALOG)
    private boolean catalog;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.IS_SYSTEM)
    private boolean system;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.READONLY)
    private boolean readonly;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.REQUIRED)
    private boolean required;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.HIDDEN)
    private boolean hidden;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.MULTIPLE)
    private boolean multiple;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.SHOW_IN_CREATE)
    private boolean showInCreate;

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

    public boolean isUseAsFilter() {
        return useAsFilter;
    }

    public void setUseAsFilter(boolean useAsFilter) {
        this.useAsFilter = useAsFilter;
    }

    public boolean isCatalog() {
        return catalog;
    }

    public void setCatalog(boolean catalog) {
        this.catalog = catalog;
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
}
