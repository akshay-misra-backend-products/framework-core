package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="attributeValues")
public class AttributeValue extends Base {

    private BigInteger attributeId;

    private int sortOrder;

    public BigInteger getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(BigInteger attributeId) {
        this.attributeId = attributeId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
