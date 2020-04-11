package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributes")
public class Attribute extends Base {

    @DBRef
    private AttributeGroup attributeGroup;

    @NotNull
    private int attributeType;

    @NotNull
    private boolean useAsFilter;

    @NotNull
    private boolean catalog;

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
}
