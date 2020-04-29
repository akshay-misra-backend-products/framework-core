package com.bss.framework.core.schema.model;

import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.bss.framework.core.schema.meta.data.annotations.RefAttr;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Akshay Misra on 11-04-2020.
 */
public class BaseExtension extends Base {

    @DBRef(lazy = true)
    @RefAttr
    @AttributeId(SystemConstants.Attributes.OBJECT_CHARACTERISTICS)
    List<ObjectCharacteristic> objectCharacteristics;

    public List<ObjectCharacteristic> getObjectCharacteristics() {
        return objectCharacteristics;
    }

    public void setObjectCharacteristics(List<ObjectCharacteristic> objectCharacteristics) {
        this.objectCharacteristics = objectCharacteristics;
    }
}
