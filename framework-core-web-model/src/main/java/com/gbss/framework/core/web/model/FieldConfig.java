package com.gbss.framework.core.web.model;

import java.util.Objects;

public class FieldConfig {

    private int type;

    private String label;

    private String name;

    private Object value;

    private boolean required;

    private boolean readonly;

    private boolean multiple;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldConfig)) return false;
        FieldConfig that = (FieldConfig) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "FieldConfig{" +
                "type=" + type +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", required=" + required +
                ", readonly=" + readonly +
                ", multiple=" + multiple +
                '}';
    }
}
