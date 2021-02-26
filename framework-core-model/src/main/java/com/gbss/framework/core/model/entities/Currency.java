package com.gbss.framework.core.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.model.constants.SystemConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="currencies")
public class Currency extends Base {

    @NotNull
    @JsonProperty(SystemConstants.Attributes.SYMBOL)
    @AttributeId(SystemConstants.Attributes.SYMBOL)
    private String symbol;

    @NotNull
    @JsonProperty(SystemConstants.Attributes.CODE)
    @AttributeId(SystemConstants.Attributes.CODE)
    private String code;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
