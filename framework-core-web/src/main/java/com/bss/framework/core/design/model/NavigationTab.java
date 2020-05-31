package com.bss.framework.core.design.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.bss.framework.core.schema.meta.data.annotations.RefAttr;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.model.ObjectType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Akshay Misra on 05-04-2020.
 */
@Document(collection="navigationTab")
public class NavigationTab extends Base {

    @JsonProperty(SystemConstants.Attributes.ICON)
    @AttributeId(SystemConstants.Attributes.ICON)
    private String icon;

    @JsonProperty(SystemConstants.Attributes.IS_CONTAINER)
    @AttributeId(SystemConstants.Attributes.IS_CONTAINER)
    private boolean container;

    @RefAttr
    @DBRef(lazy = true)
    @JsonProperty(SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPES)
    @AttributeId(SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPES)
    private List<ObjectType> objectTypes;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ObjectType> getObjectTypes() {
        return objectTypes;
    }

    public void setObjectTypes(List<ObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }

    public String toString() {
        return getClass().getName()+"[ icon: "+this.icon+", objectTypeIds: "+this.objectTypes+"]";
    }
}
