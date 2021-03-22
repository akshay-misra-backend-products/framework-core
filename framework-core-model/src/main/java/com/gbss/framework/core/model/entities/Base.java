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
public class Base extends BaseLite {

    @Version
    @VersionNumber
    @UIName("Version Number")
    @ReadOnly
    @AttributeId(SystemConstants.Attributes.VERSION)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private Long version;

    @NotBlank
    @Size(max=100)
    @Mandatory
    @UIName("Name")
    @Indexed(name = "object_name_index")
    @Name
    @AttributeId(SystemConstants.Attributes.NAME)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String name;

    @Size(max=1000)
    @UIName("Description")
    @Description
    @AttributeId(SystemConstants.Attributes.DESCRIPTION)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private String description;

    @Nullable
    @Order
    @UIName("Order Number")
    @OrderNumber
    @ReadOnly
    @AttributeId(SystemConstants.Attributes.ORDER)
    @GroupName(SystemConstants.StringLiterals.BASE_PARAMETERS)
    private int order;

    @CreatedDate
    @ReadOnly
    @Audit
    @UIName("Created At")
    @AttributeId(SystemConstants.Attributes.CREATED_AT)
    @GroupName(SystemConstants.StringLiterals.AUDIT_INFORMATION)
    private Date createdAt;

    @LastModifiedDate
    @Audit
    @ReadOnly
    @UIName("Last Modified At")
    @AttributeId(SystemConstants.Attributes.LAST_MODIFIED_AT)
    @GroupName(SystemConstants.StringLiterals.AUDIT_INFORMATION)
    private Date lastModifiedAt;



    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Base{"
                + super.toString()
                + ", version=" + version
                + ", name= " + name
                + ", description= " + description
                + ", order=" + order
                + ", createdAt=" + createdAt
                + ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }
}
