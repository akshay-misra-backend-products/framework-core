package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.impl.utils.CommonUtils;
import com.gbss.framework.core.meta.annotations.Audit;
import com.gbss.framework.core.meta.annotations.GroupName;
import com.gbss.framework.core.meta.annotations.Hidden;
import com.gbss.framework.core.meta.annotations.Mandatory;
import com.gbss.framework.core.meta.annotations.ReadOnly;
import com.gbss.framework.core.meta.annotations.UIName;
import com.gbss.framework.core.meta.annotations.base.Description;
import com.gbss.framework.core.meta.annotations.base.Name;
import com.gbss.framework.core.meta.annotations.base.ObjectTypeId;
import com.gbss.framework.core.meta.annotations.base.OrderNumber;
import com.gbss.framework.core.meta.annotations.base.ParentId;
import com.gbss.framework.core.meta.annotations.base.PublicName;
import com.gbss.framework.core.meta.annotations.base.VersionNumber;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.*;
import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.api.service.ApplicationLayoutService;
import com.gbss.framework.core.web.model.fields.AttachmentFieldConfig;
import com.gbss.framework.core.web.model.fields.ColorFieldConfig;
import com.gbss.framework.core.web.model.fields.CurrencyFieldConfig;
import com.gbss.framework.core.web.model.fields.DateFieldConfig;
import com.gbss.framework.core.web.model.fields.EmailFieldConfig;
import com.gbss.framework.core.web.model.fields.FieldType;
import com.gbss.framework.core.web.model.fields.IpAddressFieldConfig;
import com.gbss.framework.core.web.model.fields.ListFieldConfig;
import com.gbss.framework.core.web.model.fields.NumberFieldConfig;
import com.gbss.framework.core.web.model.fields.PasswordFieldConfig;
import com.gbss.framework.core.web.model.fields.ReferenceFieldConfig;
import com.gbss.framework.core.web.model.fields.TextBoxFieldConfig;
import com.gbss.framework.core.web.model.fields.TextareaFieldConfig;
import com.gbss.framework.core.web.impl.service.RestRouteCalculationServiceImpl;
import com.gbss.framework.core.impl.meta.data.MetadataHelper;
import com.gbss.framework.core.impl.repositories.AttributeRepository;
import com.gbss.framework.core.impl.repositories.AttributeValueRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.web.model.DynamicFormConfig;
import com.gbss.framework.core.web.model.FieldConfig;
import com.gbss.framework.core.web.model.GroupConfig;
import com.gbss.framework.core.web.model.NavigationTab;
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
import java.util.stream.Collectors;

@Service
public class DynamicFormComposer<T extends DynamicFormConfig> implements Composer<T> {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    AttributeSchemaService attributeSchemaService;

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

    @Autowired
    CommonUtils commonUtils;

    @Override
    public T compose(String parentObjectTypeId,
                     String parentId,
                     String objectTypeId,
                     String objectId) throws ObjectNotFoundException {
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
        List<GroupConfig> groupConfigs = getBaseGroupConfigs(baseFields, objectType, parentObjectTypeId, parentId);
        fields.removeAll(baseFields);
        List<Attribute> extendedAttributes = objectType.getAttributes();
        if (!SystemConstants.Objects.PARENT_ID_FAKE.equals(parentId)) {
            Base parent = entityBuilder.getObjectById(parentObjectTypeId, parentId);
            System.out.println("********** DynamicFormComposer, parent: "+ parent);
            if (SystemConstants.ObjectTypes.MODULE.equals(parent.getObjectTypeId())) {
                Module module = (Module) parent;
                if (module.getAttributeExtension() != null
                        && SystemConstants.ObjectTypes.ATTRIBUTE.equals(objectTypeId)) {
                    extendedAttributes.addAll(module.getAttributeExtension());
                }
            }
        }
        if (CollectionUtils.isNotEmpty(extendedAttributes)) {
            groupConfigs.addAll(getExtendedGroupConfigs(extendedAttributes));
        }
        formConfig.setGroups(groupConfigs);

        String createAPI = commonUtils.getFrameworkApi(objectType.getAddAPI());
        formConfig.setCreateAPI(createAPI);

        formConfig.setCancelLink(getCancelLink(parentObjectTypeId, parentId, objectType));

        return (T) formConfig;
    }

    private String getCancelLink(String parentObjectTypeId, String parentId, ObjectType objectType) throws ObjectNotFoundException {
        System.out.println("******* %%%% getCancelLink, parentObjectTypeId:"
                + parentObjectTypeId + ", parentId: " + parentId);
        String cancelLink = null;
        if  (parentId == null || SystemConstants.Objects.PARENT_ID_FAKE.equals(parentId)) {
            if (objectType.getTabId() != null) {
                NavigationTab navItem = applicationLayoutService.getTabById(objectType.getTabId());
                cancelLink = restRouteCalculationService.getNavItemRoute(navItem);
            } else  {
                NavigationTab navItem = applicationLayoutService.getTabById(
                        SystemConstants.Objects.DYNAMIC_OBJECTS_NAVIGATION_ID);
                cancelLink = restRouteCalculationService.getNavItemRoute(navItem);
            }
        } else {
            //when form opened from details.
            Base base = entityBuilder.getObjectById(parentObjectTypeId, parentId);
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
            String loadAPI = commonUtils.constructApiUrl(attribute.getReferenceToObjectType(),
                    "/application/api/" + attribute.getReferenceToObjectType()+"/load/all");
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
        } else if (FieldType.REFERENCE_ID.value == attribute.getAttributeType()) {
            fieldConfig = new ReferenceFieldConfig();
            String loadAPI = commonUtils.constructApiUrl(attribute.getReferenceToObjectType(),
                    "/application/api/" + attribute.getReferenceToObjectType()+"/load/all");
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
        } else if (FieldType.LIST.value == attribute.getAttributeType()) {
            List<ListFieldConfig.Options> options = new ArrayList<>();
            fieldConfig = new ListFieldConfig();
            if (SystemConstants.Attributes.ATTRIBUTE_TYPE.equals(attribute.getId())) {
                options.addAll(getAttributeTypeOptions());
            } else {
                List<AttributeValue> attributeValues = attributeValueRepository.findByParentId(attribute.getId());
                options.addAll(attributeValues.stream()
                        .map(attributeValue -> new ListFieldConfig.Options(attributeValue.getId(),
                                attributeValue.getPublicName()))
                        .collect(Collectors.toList()));
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
        ListFieldConfig.Options keyValue = new ListFieldConfig.Options(14, "Key Value");
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
        options.add(keyValue);

        return options;
    }

    private List<GroupConfig> getBaseGroupConfigs(Set<Field> baseFields,
                                                  ObjectType objectType,
                                                  String parentObjectTypeId,
                                                  String parentId) throws ObjectNotFoundException {
        List<Field> visibleBaseFields = metadataHelper.getFieldsWithoutAnnotations(baseFields,
                Arrays.asList(Hidden.class, Audit.class, VersionNumber.class));
        if (objectType.isSameTypeChildren() || parentId != null) {
            visibleBaseFields.addAll(metadataHelper.getFields(BaseLite.class, ImmutableSet.of("parentId")));
        }
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();
        for (Field field : visibleBaseFields) {
            GroupName groupNameAn = field.getAnnotation(GroupName.class);
            if (groupNameAn != null && !StringUtils.isEmpty(groupNameAn.value())) {
                if (groupToFieldConfig.containsKey(groupNameAn.value())) {
                    groupToFieldConfig.get(groupNameAn.value()).add(getBaseFieldConfig(field, objectType,
                            parentObjectTypeId, parentId));
                } else {
                    List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                    fieldConfigs1.add(getBaseFieldConfig(field, objectType, parentObjectTypeId, parentId));
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

    private FieldConfig getBaseFieldConfig(Field field, ObjectType objectType, String parentObjectTypeId,
                                           String parentId) throws ObjectNotFoundException {
        FieldConfig fieldConfig = getFieldConfigInstance(field, objectType, parentObjectTypeId, parentId);
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

    private FieldConfig getFieldConfigInstance(Field field, ObjectType objectType, String parentObjectTypeId,
                                               String parentId) throws ObjectNotFoundException {
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
                 if (!SystemConstants.Objects.PARENT_ID_FAKE.equals(parentId)) {
                     Base parent = entityBuilder.getObjectById(parentObjectTypeId, parentId);
                     fieldConfig.setValue(parent);
                 }
                fieldConfig.setReadonly(true);
            }

             if (objectType.isSameTypeChildren()) {
                 String loadAPI = commonUtils.constructApiUrl(objectType,
                         "/application/api/" + objectType.getId() + "/load/all");
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
