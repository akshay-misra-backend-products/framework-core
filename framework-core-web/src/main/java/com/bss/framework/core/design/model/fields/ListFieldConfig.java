package com.bss.framework.core.design.model.fields;

import com.bss.framework.core.design.model.FieldConfig;

import java.util.List;
import java.util.Map;

public class ListFieldConfig extends FieldConfig {

    private List<Options> options;

    public ListFieldConfig() {
        setType(FieldType.LIST.value);
        setMultiple(false);
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public static class Options {

        public Options(Object id, String name) {
            this.id = id;
            this.name = name;
        }

        Object id;

        String name;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
