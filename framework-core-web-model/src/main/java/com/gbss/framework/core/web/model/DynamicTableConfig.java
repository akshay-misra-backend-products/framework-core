package com.gbss.framework.core.web.model;

import java.util.List;

public class DynamicTableConfig {

    private String name;

    private String loadAPI;

    private String createAPI;

    private String deleteAPI;

    private String detailsAPI;

    private boolean createByObjectType;

    private List<DynamicTableConfig.Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoadAPI() {
        return loadAPI;
    }

    public void setLoadAPI(String loadAPI) {
        this.loadAPI = loadAPI;
    }

    public String getCreateAPI() {
        return createAPI;
    }

    public void setCreateAPI(String createAPI) {
        this.createAPI = createAPI;
    }

    public String getDeleteAPI() {
        return deleteAPI;
    }

    public void setDeleteAPI(String deleteAPI) {
        this.deleteAPI = deleteAPI;
    }

    public String getDetailsAPI() {
        return detailsAPI;
    }

    public void setDetailsAPI(String detailsAPI) {
        this.detailsAPI = detailsAPI;
    }

    public boolean isCreateByObjectType() {
        return createByObjectType;
    }

    public void setCreateByObjectType(boolean createByObjectType) {
        this.createByObjectType = createByObjectType;
    }

    public List<DynamicTableConfig.Column> getColumns() {
        return columns;
    }

    public void setColumns(List<DynamicTableConfig.Column> columns) {
        this.columns = columns;
    }

    public static class Column {

        private String columnDef;

        private String header;

        private boolean required;

        private boolean readonly;

        public String getColumnDef() {
            return columnDef;
        }

        public void setColumnDef(String columnDef) {
            this.columnDef = columnDef;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
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
    }
}

