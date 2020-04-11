package com.bss.framework.core.schema.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Akshay Misra on 09-04-2020.
 */
@Document(collection="objectCharacteristics")
public class ObjectCharacteristic extends Base {

    private String attributeId;

    private String objectId;

    private String value;

    @DBRef(lazy = true)
    private AttributeValue attributeValue;
}
