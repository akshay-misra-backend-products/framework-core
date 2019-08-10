package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Rocky on 15-06-2019.
 */
@Document(collection="attributes")
public class Attribute extends Base {

    @DBRef
    private AttributeGroup attributeGroup;

    @DBRef(lazy = true)
    private List<AttributeValue> attributeValues;

    @NotBlank
    private int attributeType;

    @NotBlank
    private boolean useAsFilter;

    @NotBlank
    private boolean catalog;

    private int sortOrder;

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
