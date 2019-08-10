package com.bss.framework.core.schema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Rocky on 15-06-2019.
 */
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Base {

    @Id
    private String id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String name;

    @Nullable
    @Size(min=5)
    private String publicName;

    @Size(max=1000)
    private String description;

    private Date createdAt = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String toString() {
        return getClass().getName()+"[ id: "+id+", name: "+name+", description: "+description+" ]";
    }
}
