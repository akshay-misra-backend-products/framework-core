package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

public class IpAddressFieldConfig extends FieldConfig {

    public IpAddressFieldConfig() {
        setType(FieldType.IPADDRESS.value);
    }
}
