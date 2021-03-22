package com.gbss.framework.core.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.BooleanAttr;
import com.gbss.framework.core.meta.annotations.RefAttr;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Akshay Misra on 28-02-2021.
 */
@Document(collection="modules")
public class Module extends Base {

    @NotNull
    @BooleanAttr(
            trueId  = SystemConstants.TrueFalseList.TRUE_ID,
            falseId = SystemConstants.TrueFalseList.FALSE_ID
    )
    @JsonProperty(SystemConstants.Attributes.CORE_MODULE)
    @AttributeId(SystemConstants.Attributes.CORE_MODULE)
    private boolean coreModule;

    @DBRef(lazy = true)
    @RefAttr
    @JsonProperty(SystemConstants.Attributes.ATTRIBUTE_EXTENSION)
    @AttributeId(SystemConstants.Attributes.ATTRIBUTE_EXTENSION)
    private List<Attribute> attributeExtension;

    public boolean isCoreModule() {
        return coreModule;
    }

    public void setCoreModule(boolean coreModule) {
        this.coreModule = coreModule;
    }

    public List<Attribute> getAttributeExtension() {
        return attributeExtension;
    }

    public void setAttributeExtension(List<Attribute> attributeExtension) {
        this.attributeExtension = attributeExtension;
    }
}
