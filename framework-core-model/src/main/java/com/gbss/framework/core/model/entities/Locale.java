package com.gbss.framework.core.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.RefAttr;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="locales")
public class Locale extends Base {

    @DBRef
    @NotNull
    @RefAttr
    @JsonProperty(SystemConstants.Attributes.CURRENCY)
    @AttributeId(SystemConstants.Attributes.CURRENCY)
    private Currency currency;

    //date format
    //week start day
    //new year
    //...... check if attributes required for all above or it can be done by using JAVA APIs.

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
