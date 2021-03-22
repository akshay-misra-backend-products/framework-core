package com.gbss.framework.core.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection="microservices")
public class Microservice extends Base {

    @NotBlank
    @JsonProperty(SystemConstants.Attributes.SERVICE_NAME)
    @AttributeId(SystemConstants.Attributes.SERVICE_NAME)
    private String serviceName;

    @NotBlank
    @JsonProperty(SystemConstants.Attributes.SERVICE_TYPE)
    @AttributeId(SystemConstants.Attributes.SERVICE_TYPE)
    private String serviceType;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
