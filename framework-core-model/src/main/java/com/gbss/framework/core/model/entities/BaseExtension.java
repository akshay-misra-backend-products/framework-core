package com.gbss.framework.core.model.entities;

import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.RefAttr;
import com.gbss.framework.core.model.constants.SystemConstants;
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
