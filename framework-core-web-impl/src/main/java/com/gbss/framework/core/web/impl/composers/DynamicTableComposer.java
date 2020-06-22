package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.meta.annotations.Hidden;
import com.gbss.framework.core.meta.annotations.Mandatory;
import com.gbss.framework.core.meta.annotations.ReadOnly;
import com.gbss.framework.core.meta.annotations.UIName;
import com.gbss.framework.core.meta.annotations.base.Description;
import com.gbss.framework.core.meta.annotations.base.Name;
import com.gbss.framework.core.meta.annotations.base.OrderNumber;
import com.gbss.framework.core.meta.annotations.base.PublicName;
import com.gbss.framework.core.meta.annotations.base.VersionNumber;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.model.DynamicTableConfig;
import com.gbss.framework.core.web.model.FieldConfig;
import com.gbss.framework.core.web.model.fields.DateFieldConfig;
import com.gbss.framework.core.web.model.fields.NumberFieldConfig;
import com.gbss.framework.core.web.model.fields.TextBoxFieldConfig;
import com.gbss.framework.core.web.model.fields.TextareaFieldConfig;
import com.gbss.framework.core.impl.meta.data.MetadataHelper;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class DynamicTableComposer<T extends DynamicTableConfig> implements Composer<T> {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    MetadataHelper metadataHelper;

    // TODO: read all rest apis from ObjectType. [rest path will be different for system OTs and non system OTs like (CoreObjects)]
    // TODO: for system OT: /application/api/objectTypeId/load....
    // TODO: for non system OT: /application/api/load/{objectTypeId}/{objectId}
    @Override
    public T compose(String objectTypeId, String objectId) {
        System.out.println("********** DynamicTableComposer, compose, objectId: " + objectId);
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        System.out.println("********** DynamicTableComposer, compose, objectType: " + objectType);
        DynamicTableConfig dynamicTableConfig = new DynamicTableConfig();
        dynamicTableConfig.setName(objectType.getName());
        String loadAPI = null;
        if (objectId == null) {
            loadAPI = "/application/api/" + objectType.getId() + "/load/all";
        } else {
            loadAPI = "/application/api/" + objectType.getId() + "/load/by/parent/" + objectId;
        }
        System.out.println("----- DynamicTableComposer, compose, loadAPI: " + loadAPI);
        dynamicTableConfig.setLoadAPI(loadAPI);
        String createAPI = null;
        if (objectType.getTabId() != null &&
                SystemConstants.Objects.DYNAMIC_OBJECTS_NAVIGATION_ID.equals(objectType.getTabId())) {
            dynamicTableConfig.setCreateByObjectType(true);
        } else {
            if (objectId == null) {
                createAPI = "/application/design/create/object/" + objectType.getId() + "/"
                        + SystemConstants.Objects.FAKE_OBJECT;
            } else {
                createAPI = "/application/design/create/object/" + objectType.getId() + "/" + objectId;
            }
        }
        dynamicTableConfig.setCreateAPI(createAPI);
        String deleteAPI = "/application/api/" + objectType.getId() + "/delete/";
        dynamicTableConfig.setDeleteAPI(deleteAPI);
        String detailsAPI = "/application/api/load/details/" + objectType.getId() + "/";
        dynamicTableConfig.setDetailsAPI(detailsAPI);
        List<DynamicTableConfig.Column> columns = getColumns();
        System.out.println("********** DynamicTableComposer, compose, columns: " + columns);
        dynamicTableConfig.setColumns(columns);

        return (T) dynamicTableConfig;
    }

    private List<DynamicTableConfig.Column> getColumns() {
        List<DynamicTableConfig.Column> columns = new ArrayList<>();
        Set<Field> baseFields = metadataHelper.getBaseFields();
        List<Field> visibleBaseFields = metadataHelper.getFieldsWithoutAnnotation(baseFields, Hidden.class);
        for (Field field : visibleBaseFields) {
            FieldConfig fieldConfig = getBaseFieldConfig(field);
            DynamicTableConfig.Column column = new DynamicTableConfig.Column();
            column.setColumnDef(fieldConfig.getName());
            column.setHeader(fieldConfig.getLabel());
            column.setReadonly(fieldConfig.isReadonly());
            column.setRequired(fieldConfig.isRequired());

            columns.add(column);
        }
      return columns;
    }

    private FieldConfig getBaseFieldConfig(Field field) {
        FieldConfig fieldConfig = getFieldConfigInstance(field);
        UIName uiNameAn = field.getAnnotation(UIName.class);
        if (uiNameAn != null) {
            String name = uiNameAn.value();
            System.out.println("********** DynamicTableComposer, getFieldConfigsByField, UI name: "+ name);
            fieldConfig.setLabel(name);
            fieldConfig.setName(field.getName());
            fieldConfig.setMultiple(false);
            fieldConfig.setReadonly(field.isAnnotationPresent(ReadOnly.class));
            fieldConfig.setRequired(field.isAnnotationPresent(Mandatory.class));
        }
        return fieldConfig;
    }

    private FieldConfig getFieldConfigInstance(Field field) {
        FieldConfig fieldConfig;

        if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(VersionNumber.class, OrderNumber.class))) {
            fieldConfig = new NumberFieldConfig();
        } else if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(Name.class, PublicName.class))) {
            fieldConfig = new TextBoxFieldConfig();
        } else if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(CreatedDate.class, LastModifiedDate.class))) {
            fieldConfig = new DateFieldConfig();
        } else if (metadataHelper.hasAnnotation(field, Description.class)) {
            fieldConfig = new TextareaFieldConfig();
        } else {
            fieldConfig = new TextBoxFieldConfig();
        }

        return fieldConfig;
    }
}
