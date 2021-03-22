package com.gbss.framework.core.model.entities;

import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.model.constants.SystemConstants;

import java.util.HashMap;
import java.util.Map;

/**
* Objects from any microservice will have this schema.
*/
//@Document(collection="dynamicObjects")
public class DynamicObject extends Base {

    private String collectionName;

    @AttributeId(SystemConstants.Attributes.DYNAMIC_PARAMETERS)
    private Map<String, Object> extendedParameters = new HashMap<>();

    public Map<String, Object> getExtendedParameters() {
        return extendedParameters;
    }

    public void setExtendedParameters(Map<String, Object> extendedParameters) {
        this.extendedParameters = extendedParameters;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return super.toString() + ", DynamicObject [" +
                "extendedParameters=" + extendedParameters +
                ']';
    }
}
