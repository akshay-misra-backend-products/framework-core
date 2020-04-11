package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Akshay Misra on 11-04-2020.
 */
public class BaseExtension extends Base {

    @DBRef(lazy = true)
    List<ObjectCharacteristic> objectCharacteristics;

    public List<ObjectCharacteristic> getObjectCharacteristics() {
        return objectCharacteristics;
    }

    public void setObjectCharacteristics(List<ObjectCharacteristic> objectCharacteristics) {
        this.objectCharacteristics = objectCharacteristics;
    }
}
