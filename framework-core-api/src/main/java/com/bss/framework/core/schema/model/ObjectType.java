package com.bss.framework.core.schema.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.bss.framework.core.schema.meta.data.annotations.BooleanAttr;
import com.bss.framework.core.schema.meta.data.annotations.Hidden;
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
    @AttributeId(SystemConstants.Attributes.ATTRIBUTES)
    private List<Attribute> attributes;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.SAME_TYPE_CHILDREN)
    private boolean sameTypeChildren;

    @Hidden
    private String loadAPI;

    @Hidden
    private String loadByIdAPI;

    @Hidden
    private String addAPI;

    @Hidden
    private String updateAPI;

    @Hidden
    private String deleteAPI;

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @AttributeId(SystemConstants.Attributes.IS_SYSTEM)
    private boolean system;

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

    public String getLoadByIdAPI() {
        return loadByIdAPI;
    }

    public void setLoadByIdAPI(String loadByIdAPI) {
        this.loadByIdAPI = loadByIdAPI;
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
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String toString() {
        return super.toString()+"   "+getClass().getName()+"[ attributes: "+this.attributes+", sameTypeChildren: "+this.sameTypeChildren+", isSystem: "+this.system+" ]";
    }
}
