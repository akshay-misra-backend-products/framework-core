package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@Document(collection="objectTypes")
public class ObjectType extends Base {

    @DBRef(lazy = true)
    private List<Attribute> attributes;

    @NotNull
    private boolean sameTypeChildren;

    private String loadAPI;

    private String addAPI;

    private String updateAPI;

    private String deleteAPI;

    @NotNull
    private boolean isSystem;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isSameTypeChildren() {
        return sameTypeChildren;
    }

    public void setSameTypeChildren(boolean sameTypeChildren) {
        this.sameTypeChildren = sameTypeChildren;
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

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }
}
