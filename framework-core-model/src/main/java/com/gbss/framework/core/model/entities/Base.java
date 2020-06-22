package com.gbss.framework.core.model.entities;

import com.gbss.framework.core.meta.annotations.*;
import com.gbss.framework.core.meta.annotations.base.*;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Akshay Misra on 15-06-2019.
 */
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Base {

    @Id
    @ObjectId
    @Hidden
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String id;

    @Version
    @VersionNumber
    @UIName("Version Number")
    @ReadOnly
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private Long version;

    @NotBlank
    @UIName("Object Type")
    @RefIdAttr
    @ObjectTypeId
    //@Hidden
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String objectTypeId;

    @ParentId
    @UIName("Parent")
    @Hidden
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String parentId;

    @NotBlank
    @Size(max=100)
    @Mandatory
    @UIName("Name")
    @Indexed(name = "object_name_index")
    @Name
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String name;

    @Nullable
    @UIName("Public Name")
    @Size(max=100)
    @Indexed(name = "object_public_name_index")
    @PublicName
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String publicName;

    @Size(max=1000)
    @UIName("Description")
    @Description
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String description;

    @Nullable
    @Order
    @UIName("Order Number")
    @OrderNumber
    @ReadOnly
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private int order;

    @CreatedDate
    @ReadOnly
    @Audit
    @UIName("Created At")
    @GroupName(SystemConstants.StringLiterals.AUDIT_INFORMATION)
    @AttributeId("-70")
    private Date createdAt;

    @LastModifiedDate
    @Audit
    @ReadOnly
    @UIName("Last Modified At")
    @GroupName(SystemConstants.StringLiterals.AUDIT_INFORMATION)
    @AttributeId("-71")
    private Date lastModifiedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String toString() {
        return getClass().getName()+"[ id: "+id+", name: "+name+", description: "+description+", version: "+ version+ " ]";
    }
}
