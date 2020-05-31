package com.bss.framework.core.design.composers;

import com.bss.framework.core.design.model.*;
import com.bss.framework.core.design.model.fields.*;
import com.bss.framework.core.design.service.api.ApplicationLayoutService;
import com.bss.framework.core.design.service.impl.RestRouteCalculationServiceImpl;
import com.bss.framework.core.schema.constants.SystemConstants;
import com.bss.framework.core.schema.factory.ObjectTypeRepositoryMapperFactory;
import com.bss.framework.core.schema.meta.data.MetadataHelper;
import com.bss.framework.core.schema.meta.data.annotations.*;
import com.bss.framework.core.schema.meta.data.annotations.base.*;
import com.bss.framework.core.schema.model.Attribute;
import com.bss.framework.core.schema.model.AttributeValue;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.model.ObjectType;
import com.bss.framework.core.schema.repositories.AttributeRepository;
import com.bss.framework.core.schema.repositories.AttributeValueRepository;
import com.bss.framework.core.schema.repositories.ObjectTypeRepository;
import com.bss.framework.core.schema.utils.EntityBuilder;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ejb.ObjectNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

@Service
public class DynamicFormComposer<T extends DynamicFormConfig> implements Composer<T> {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    ApplicationLayoutService applicationLayoutService;

    @Autowired
    RestRouteCalculationServiceImpl restRouteCalculationService;

    @Autowired
    MetadataHelper metadataHelper;

    @Autowired
    EntityBuilder entityBuilder;

    @Override
    public T compose(String objectTypeId, String parentId) throws ObjectNotFoundException {
        if (SystemConstants.Objects.FAKE_OBJECT.equals(parentId)) {
            parentId = null;
        }

        DynamicFormConfig formConfig = new DynamicFormConfig();

        ObjectType objectType = objectTypeRepository.findById(objectTypeId).get();
        System.out.println("********** DynamicFormComposer, objectType: "+ objectType);
        formConfig.setObjectTypeId(objectTypeId);
        StringBuilder formTitle = new StringBuilder();
        formTitle.append("New ");
        formTitle.append(objectType.getName());
        formConfig.setFormTitle(formTitle.toString());

        Set<Field> fields = metadataHelper.getFields(objectType.getClass());
        System.out.println("********** DynamicFormComposer,  fields size: "+ fields.size());
        Set<Field> baseFields = metadataHelper.getBaseFields();
        System.out.println("********** DynamicFormComposer,  baseFields size: "+ baseFields.size());
        List<GroupConfig> groupConfigs = getBaseGroupConfigs(baseFields, objectType, parentId);
        fields.removeAll(baseFields);
        List<Attribute> extendedAttributes = objectType.getAttributes();
        if (CollectionUtils.isNotEmpty(extendedAttributes)) {
            groupConfigs.addAll(getExtendedGroupConfigs(extendedAttributes));
        }
        formConfig.setGroups(groupConfigs);

        String createAPI = "/application/api/"+objectTypeId+"/add";
        formConfig.setCreateAPI(createAPI);

        formConfig.setCancelLink(getCancelLink(objectType, parentId));

        return (T) formConfig;
    }

    private String getCancelLink(ObjectType objectType, String objectId) throws ObjectNotFoundException {
        String cancelLink = null;
        if  (objectId == null || SystemConstants.Objects.FAKE_OBJECT.equals(objectId)) {
            if (objectType.getTabId() != null) {
                NavigationTab navItem = applicationLayoutService.getTabById(objectType.getTabId());
                cancelLink = restRouteCalculationService.getNavItemRoute(navItem);
            }
        } else {
            //when form opened from details.
            Base base = entityBuilder.getObjectByChildOrCurrentOT(objectType.getId(), objectId);
            cancelLink = restRouteCalculationService.getObjectDetailsRoute(base);
        }

        return cancelLink;
    }

    private List<GroupConfig> getExtendedGroupConfigs(List<Attribute> attributes) {
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();

        for (Attribute attribute : attributes) {
            System.out.println("********** DynamicFormComposer, getExtendedGroupConfigs, attribute: " + attribute.getName());
            String groupName = attribute.getAttributeGroup().getPublicName();
            if (groupToFieldConfig.containsKey(groupName)) {
                groupToFieldConfig.get(groupName).add(getExtendedFieldConfig(attribute));
            } else {
                List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                fieldConfigs1.add(getExtendedFieldConfig(attribute));
                groupToFieldConfig.put(groupName, fieldConfigs1);
            }
        }

        System.out.println("********** DynamicFormComposer, getExtendedGroupConfigs, groupToFieldConfig: "+ groupToFieldConfig);
        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        System.out.println("********** DynamicFormComposer, getExtendedGroupConfigs, groupConfigs: "+ groupConfigs);

        return groupConfigs;
    }

    private FieldConfig getExtendedFieldConfig(Attribute attribute) {
        FieldConfig fieldConfig = getExtendedFieldConfigInstance(attribute);
        fieldConfig.setLabel(attribute.getPublicName());
        fieldConfig.setName(attribute.getId());
        fieldConfig.setMultiple(attribute.isMultiple());
        fieldConfig.setReadonly(attribute.isReadonly());
        fieldConfig.setRequired(attribute.isRequired());

        return fieldConfig;
    }

    private FieldConfig getExtendedFieldConfigInstance(Attribute attribute) {
        FieldConfig fieldConfig;

        if (FieldType.TEXT.value == attribute.getAttributeType()) {
            fieldConfig = new TextBoxFieldConfig();
        } else if (FieldType.NUMBER.value == attribute.getAttributeType()) {
            fieldConfig = new NumberFieldConfig();
        } else if (FieldType.REFERENCE.value == attribute.getAttributeType()) {
            fieldConfig = new ReferenceFieldConfig();
            String loadAPI = "/application/api/" + attribute.getReferenceToObjectType()+"/load/all";
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
        } else if (FieldType.REFERENCE_ID.value == attribute.getAttributeType()) {
            fieldConfig = new ReferenceFieldConfig();
            String loadAPI = "/application/api/" + attribute.getReferenceToObjectType()+"/load/all";
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
        } else if (FieldType.LIST.value == attribute.getAttributeType()) {
            List<ListFieldConfig.Options> options = new ArrayList<>();
            fieldConfig = new ListFieldConfig();
            if (SystemConstants.Attributes.ATTRIBUTE_TYPE.equals(attribute.getId())) {
                options.addAll(getAttributeTypeOptions());
            } else {
                List<AttributeValue> attributeValues = attributeValueRepository.findByParentId(attribute.getId());
                System.out.println("********** DynamicFormComposer, getExtendedFieldConfig, attributeValues: " + attributeValues);
                attributeValues.stream()
                        .map(attributeValue -> {
                            ListFieldConfig.Options option = new ListFieldConfig.Options(attributeValue.getId(),
                                    attributeValue.getPublicName());
                            options.add(option);
                            return null;
                        });
            }
            System.out.println("********** DynamicFormComposer, getExtendedFieldConfig, options: "+ options);
            ((ListFieldConfig) fieldConfig).setOptions(options);
        } else if (FieldType.BOOLEAN.value == attribute.getAttributeType()) {
            List<ListFieldConfig.Options> options = new ArrayList<>();
            fieldConfig = new ListFieldConfig();
            ListFieldConfig.Options trueValue = new ListFieldConfig.Options(true, "Yes");
            ListFieldConfig.Options falseValue = new ListFieldConfig.Options(false, "No");
            options.add(trueValue);
            options.add(falseValue);
            ((ListFieldConfig) fieldConfig).setOptions(options);
        } else if (FieldType.TEXTAREA.value == attribute.getAttributeType()) {
            fieldConfig = new TextareaFieldConfig();
        } else if (FieldType.DATE.value == attribute.getAttributeType()) {
            fieldConfig = new DateFieldConfig();
        } else if (FieldType.CURRENCY.value == attribute.getAttributeType()) {
            fieldConfig = new CurrencyFieldConfig();
        } else if (FieldType.IPADDRESS.value == attribute.getAttributeType()) {
            fieldConfig = new IpAddressFieldConfig();
        } else if (FieldType.ATTACHMENT.value == attribute.getAttributeType()) {
            fieldConfig = new AttachmentFieldConfig();
        } else if (FieldType.EMAIL.value == attribute.getAttributeType()) {
            fieldConfig = new EmailFieldConfig();
        } else if (FieldType.PASSWORD.value == attribute.getAttributeType()) {
            fieldConfig = new PasswordFieldConfig();
        } else if (FieldType.COLOR.value == attribute.getAttributeType()) {
            fieldConfig = new ColorFieldConfig();
        } else {
            fieldConfig = new TextBoxFieldConfig();
        }

        return fieldConfig;
    }

    private List<ListFieldConfig.Options> getAttributeTypeOptions() {
        List<ListFieldConfig.Options> options = new ArrayList<>();
        ListFieldConfig.Options text = new ListFieldConfig.Options(0, "Text");
        ListFieldConfig.Options number = new ListFieldConfig.Options(1, "Number");
        ListFieldConfig.Options reference = new ListFieldConfig.Options(2, "Reference");
        ListFieldConfig.Options list = new ListFieldConfig.Options(3, "List");
        ListFieldConfig.Options memo = new ListFieldConfig.Options(4, "Memo");
        ListFieldConfig.Options date = new ListFieldConfig.Options(5, "Date");
        ListFieldConfig.Options currency = new ListFieldConfig.Options(6, "Currency");
        ListFieldConfig.Options ipAddress = new ListFieldConfig.Options(7, "Ip Address");
        ListFieldConfig.Options attachment = new ListFieldConfig.Options(8, "Attachment");
        ListFieldConfig.Options email = new ListFieldConfig.Options(9, "Email");
        ListFieldConfig.Options password = new ListFieldConfig.Options(10, "Password");
        ListFieldConfig.Options color = new ListFieldConfig.Options(11, "Color");
        ListFieldConfig.Options refId = new ListFieldConfig.Options(12, "Reference Id");
        ListFieldConfig.Options booleanList = new ListFieldConfig.Options(13, "Yes/No List");
        options.add(text);
        options.add(number);
        options.add(reference);
        options.add(list);
        options.add(memo);
        options.add(date);
        options.add(currency);
        options.add(ipAddress);
        options.add(attachment);
        options.add(email);
        options.add(password);
        options.add(color);
        options.add(refId);
        options.add(booleanList);

        return options;
    }

    private List<GroupConfig> getBaseGroupConfigs(Set<Field> baseFields,
                                                  ObjectType objectType,
                                                  String parentId) throws ObjectNotFoundException {
        List<Field> visibleBaseFields = metadataHelper.getFieldsWithoutAnnotations(baseFields,
                Arrays.asList(Hidden.class, Audit.class, VersionNumber.class));
        if (objectType.isSameTypeChildren() || parentId != null) {
            visibleBaseFields.addAll(metadataHelper.getFields(Base.class, ImmutableSet.of("parentId")));
        }
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();
        for (Field field : visibleBaseFields) {
            GroupName groupNameAn = field.getAnnotation(GroupName.class);
            if (groupNameAn != null && !StringUtils.isEmpty(groupNameAn.value())) {
                if (groupToFieldConfig.containsKey(groupNameAn.value())) {
                    groupToFieldConfig.get(groupNameAn.value()).add(getBaseFieldConfig(field, objectType, parentId));
                } else {
                    List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                    fieldConfigs1.add(getBaseFieldConfig(field, objectType, parentId));
                    groupToFieldConfig.put(groupNameAn.value(), fieldConfigs1);
                }
            }
        }
        System.out.println("********** DynamicFormComposer, getBaseGroupConfigs, groupToFieldConfig: "+ groupToFieldConfig);
        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        System.out.println("********** DynamicFormComposer, getBaseGroupConfigs, groupConfigs: "+ groupConfigs);

        return groupConfigs;
    }

    private FieldConfig getBaseFieldConfig(Field field, ObjectType objectType, String parentId) throws ObjectNotFoundException {
        FieldConfig fieldConfig = getFieldConfigInstance(field, objectType, parentId);
        UIName uiNameAn = field.getAnnotation(UIName.class);
        if (uiNameAn != null) {
            String name = uiNameAn.value();
            System.out.println("********** DynamicFormComposer, getFieldConfigsByField, UI name: "+ name);
            fieldConfig.setLabel(name);
            fieldConfig.setName(field.getName());
            fieldConfig.setMultiple(false);
            fieldConfig.setReadonly(field.isAnnotationPresent(ReadOnly.class));
            fieldConfig.setRequired(field.isAnnotationPresent(Mandatory.class));
        }
        return fieldConfig;
    }

    private FieldConfig getFieldConfigInstance(Field field, ObjectType objectType, String parentId) throws ObjectNotFoundException {
        FieldConfig fieldConfig;

        if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(VersionNumber.class, OrderNumber.class))) {
            fieldConfig = new NumberFieldConfig();
        } else if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(Name.class, PublicName.class))) {
            fieldConfig = new TextBoxFieldConfig();
        } else if (metadataHelper.hasAnyAnnotation(field, Arrays.asList(CreatedDate.class, LastModifiedDate.class))) {
            fieldConfig = new DateFieldConfig();
        } else if (metadataHelper.hasAnnotation(field, Description.class)) {
            fieldConfig = new TextareaFieldConfig();
        } else if (metadataHelper.hasAnnotation(field, ParentId.class)) {
            fieldConfig = new ReferenceFieldConfig();
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
             if (parentId != null) {
                Base parent = entityBuilder.getObjectByChildOT(objectType.getId(), parentId);
                fieldConfig.setValue(parent);
                fieldConfig.setReadonly(true);
            } else if (objectType.isSameTypeChildren()) {
                 String loadAPI = "/application/api/" + objectType.getId() + "/load/all";
                 ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
             }
        } else if (metadataHelper.hasAnnotation(field, ObjectTypeId.class)) {
            fieldConfig = new ReferenceFieldConfig();
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
            fieldConfig.setReadonly(true);
            fieldConfig.setValue(objectType);
        } else {
            fieldConfig = new TextBoxFieldConfig();
        }

        return fieldConfig;
    }
}
