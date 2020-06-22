package com.gbss.framework.core.web.model.fields;

import com.gbss.framework.core.web.model.FieldConfig;

import java.util.List;

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
