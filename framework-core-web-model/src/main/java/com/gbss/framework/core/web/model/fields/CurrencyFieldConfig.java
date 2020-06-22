package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class CurrencyFieldConfig extends FieldConfig {

    public CurrencyFieldConfig() {
        setType(FieldType.CURRENCY.value);
    }
}
