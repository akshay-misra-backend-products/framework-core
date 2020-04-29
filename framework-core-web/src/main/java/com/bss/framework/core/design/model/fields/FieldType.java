package com.bss.framework.core.design.model.fields;

public enum FieldType {

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
    COLOR(11);

    public int value;
    FieldType(int value) {
        this.value = value;
    }
}


