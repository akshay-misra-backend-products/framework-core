package com.gbss.framework.core.model.constants;

public enum AttributeType {

    TEXT(0),
    NUMBER(1),
    REFERENCE(2),
    LIST(3),
    TEXTAREA(4),
    DATE(5),
    CURRENCY(6),
    IPADDRESS(7),
    ATTACHMENT(8),
    EMAIL(9),
    PASSWORD(10),
    COLOR(11),
    REFERENCE_ID(12),
    BOOLEAN(13),
    KEY_VALUE(14);

    public int value;
    AttributeType(int value) {
        this.value = value;
    }
}
