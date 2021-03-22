package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.impl.utils.CommonUtils;
import com.gbss.framework.core.meta.annotations.Hidden;
import com.gbss.framework.core.meta.annotations.Mandatory;
import com.gbss.framework.core.meta.annotations.ReadOnly;
import com.gbss.framework.core.meta.annotations.UIName;
import com.gbss.framework.core.meta.annotations.base.*;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.api.service.RestRouteCalculationService;
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

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    RestRouteCalculationService restRouteCalculationService;

    // TODO: read all rest apis from ObjectType. [rest path will be different for system OTs and non system OTs like (CoreObjects)]
    // TODO: for system OT: /application/api/objectTypeId/load....
    // TODO: for non system OT: /application/api/load/{objectTypeId}/{objectId}
    @Override
    public T compose(String parentObjectTypeId,
                     String parentId,
                     String objectTypeId,
                     String objectId) { //TODO: no use of objectId, remove if possible
        System.out.println("********** DynamicTableComposer, compose, "
                + "parentObjectTypeId: " + parentObjectTypeId
                + ", parentId: " + parentId
                + ", objectTypeId: " + objectTypeId
                + ", objectId: " + objectId);
        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        System.out.println("********** DynamicTableComposer, compose, objectType: " + objectType);
        DynamicTableConfig dynamicTableConfig = new DynamicTableConfig();
        dynamicTableConfig.setName(objectType.getName());
        dynamicTableConfig.setObjectTypeId(objectTypeId);
        String loadAPI = null;
        if (parentId == null
                || SystemConstants.Objects.FAKE_OBJECT.equals(objectId)) {
            loadAPI = objectType.getLoadAPI();
            dynamicTableConfig.setParentId(SystemConstants.Objects.PARENT_ID_FAKE);
        } else {
            loadAPI = objectType.getLoadByParentIdAPI() + parentId;
            dynamicTableConfig.setParentId(parentId);
        }
        System.out.println("----- DynamicTableComposer, compose, loadAPI: " + loadAPI);
        dynamicTableConfig.setLoadAPI(loadAPI);
        String createAPI = null;
        if (objectType.getTabId() != null &&
                SystemConstants.Objects.DYNAMIC_OBJECTS_NAVIGATION_ID.equals(objectType.getTabId())) {
            dynamicTableConfig.setCreateByObjectType(true);
        } else {
            if (parentId == null) {
                createAPI = restRouteCalculationService.getCreateObjectRoute(
                        parentObjectTypeId == null
                                ? SystemConstants.Objects.PARENT_OBJECT_TYPE_ID_FAKE : parentObjectTypeId,
                        SystemConstants.Objects.PARENT_ID_FAKE, objectTypeId);
            } else {
                createAPI = restRouteCalculationService.getCreateObjectRoute(
                        parentObjectTypeId == null
                                ? SystemConstants.Objects.PARENT_OBJECT_TYPE_ID_FAKE : parentObjectTypeId,
                        parentId == null
                        ? SystemConstants.Objects.PARENT_ID_FAKE : parentId, objectTypeId);
            }
        }
        dynamicTableConfig.setCreateAPI(createAPI);
        String deleteAPI = objectType.getDeleteAPI();
        dynamicTableConfig.setDeleteAPI(deleteAPI);
        String detailsAPI = restRouteCalculationService.getObjectDetailsRoute(
                parentObjectTypeId == null
                        ? SystemConstants.Objects.PARENT_OBJECT_TYPE_ID_FAKE : parentObjectTypeId,
                parentId == null
                        ? SystemConstants.Objects.PARENT_ID_FAKE : parentId,
                objectTypeId);
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
