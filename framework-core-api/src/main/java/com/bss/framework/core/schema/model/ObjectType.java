package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="objectTypes")
public class ObjectType extends Base {

    @DBRef(lazy = true)
    List<Attribute> attributes;

    @DBRef(lazy = true)
    List<ObjectType> childObjectTypes;

    @NotBlank
    boolean sameTypeAsChild;

    String loadAPI;

    String addAPI;

    String updateAPI;

    String deleteAPI;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<ObjectType> getChildObjectTypes() {
        return childObjectTypes;
    }

    public void setChildObjectTypes(List<ObjectType> childObjectTypes) {
        this.childObjectTypes = childObjectTypes;
    }

    public boolean isSameTypeAsChild() {
        return sameTypeAsChild;
    }

    public void setSameTypeAsChild(boolean sameTypeAsChild) {
        this.sameTypeAsChild = sameTypeAsChild;
    }

    public String getLoadAPI() {
        return loadAPI;
    }

    public void setLoadAPI(String loadAPI) {
        this.loadAPI = loadAPI;
    }

    public String getAddAPI() {
        return addAPI;
    }

    public void setAddAPI(String addAPI) {
        this.addAPI = addAPI;
    }

    public String getUpdateAPI() {
        return updateAPI;
    }

    public void setUpdateAPI(String updateAPI) {
        this.updateAPI = updateAPI;
    }

    public String getDeleteAPI() {
        return deleteAPI;
    }

    public void setDeleteAPI(String deleteAPI) {
        this.deleteAPI = deleteAPI;
    }
}
