package com.gbss.framework.core.model.entities;

import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection="dynamicObjects")
public class DynamicObject extends Base {

    @AttributeId(SystemConstants.Attributes.DYNAMIC_PARAMETERS)
    private Map<String, Object> extendedParameters = new HashMap<>();

    public Map<String, Object> getExtendedParameters() {
        return extendedParameters;
    }

    public void setExtendedParameters(Map<String, Object> extendedParameters) {
        this.extendedParameters = extendedParameters;
    }

    @Override
    public String toString() {
        return super.toString() + ", DynamicObject [" +
                "extendedParameters=" + extendedParameters +
                ']';
    }
}
