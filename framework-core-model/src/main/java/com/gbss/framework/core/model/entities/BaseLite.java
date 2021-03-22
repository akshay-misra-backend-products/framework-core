package com.gbss.framework.core.model.entities;

import com.gbss.framework.core.meta.annotations.*;
import com.gbss.framework.core.meta.annotations.base.*;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BaseLite {

    @Id
    @ObjectId
    @Hidden
    @AttributeId(SystemConstants.Attributes.ID)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String id;

    @NotBlank
    @UIName("Object Type")
    @RefIdAttr
    @ObjectTypeId
    @AttributeId(SystemConstants.Attributes.OBJECT_TYPE_ID)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String objectTypeId;

    @ParentId
    @UIName("Parent")
    @Hidden
    @AttributeId(SystemConstants.Attributes.PARENT_ID)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String parentId;

    @ParentId
    @UIName("Parent Type")
    @Hidden
    @AttributeId(SystemConstants.Attributes.PARENT_TYPE_ID)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String parentTypeId;

    @Nullable
    @UIName("Public Name")
    @Size(max=100)
    @Indexed(name = "object_public_name_index")
    @PublicName
    @AttributeId(SystemConstants.Attributes.PUBLIC_NAME)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String publicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    @Override
    public String toString() {
        return "BaseLite{" +
                "id='" + id + '\'' +
                ", objectTypeId='" + objectTypeId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentTypeId='" + parentTypeId + '\'' +
                ", publicName='" + publicName + '\'' +
                '}';
    }
}
