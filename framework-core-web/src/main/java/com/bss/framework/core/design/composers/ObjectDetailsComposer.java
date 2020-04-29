package com.bss.framework.core.design.composers;


import com.bss.framework.core.design.model.FieldConfig;
import com.bss.framework.core.design.model.GroupConfig;
import com.bss.framework.core.design.model.ObjectDetailsConfig;
import com.bss.framework.core.design.model.ObjectLayoutWrapper;
import com.bss.framework.core.design.model.fields.*;
import com.bss.framework.core.schema.factory.ObjectTypeRepositoryMapperFacade;
import com.bss.framework.core.schema.meta.data.MetadataHelper;
import com.bss.framework.core.schema.meta.data.annotations.*;
import com.bss.framework.core.schema.meta.data.annotations.base.*;
import com.bss.framework.core.schema.model.Attribute;
import com.bss.framework.core.schema.model.AttributeValue;
import com.bss.framework.core.schema.model.Base;
import com.bss.framework.core.schema.repositories.AttributeRepository;
import com.bss.framework.core.schema.repositories.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectDetailsComposer<T extends ObjectLayoutWrapper> implements Composer<T>  {

    @Autowired
    ObjectTypeRepositoryMapperFacade objectTypeRepositoryMapperFacade;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    MetadataHelper metadataHelper;

    @Override
    public T compose(String objectTypeId, String objectId) {
        ObjectLayoutWrapper objectLayoutWrapper = new ObjectLayoutWrapper();
        ObjectDetailsConfig detail = new ObjectDetailsConfig();
        objectLayoutWrapper.setDetail(detail);

        MongoRepository repository = objectTypeRepositoryMapperFacade.getRepositoryByObjectTypeId(objectTypeId);
        Base base = (Base) repository.findById(objectId).get();
        System.out.println("********** ObjectDetailsComposer, repository base fields: "+ base);
        detail.setId(objectId);
        detail.setObjectName(base.getPublicName() == null ? base.getName() : base.getPublicName());
        objectLayoutWrapper.setObjectName(base.getPublicName() == null ? base.getName() : base.getPublicName());

        Set<Field> fields = metadataHelper.getFields(base.getClass());
        System.out.println("********** ObjectDetailsComposer,  fields size: "+ fields.size());
        Set<Field> baseFields = metadataHelper.getBaseFields();
        System.out.println("********** ObjectDetailsComposer,  baseFields size: "+ baseFields.size());
        List<GroupConfig> groupConfigs = getBaseGroupConfigs(baseFields, base);
        fields.removeAll(baseFields);
        groupConfigs.addAll(getExtendedGroupConfigs(fields, base));
        detail.setGroups(groupConfigs);

        /*for (Field field : fields) {
            Object value = metadataHelper.getValue(base, base.getClass(), field);
            System.out.println("------------------value-------------------"+ value);
            generateFieldGroupConfig(field, baseFields, value);
        }*/

        return (T) objectLayoutWrapper;
    }

    private List<GroupConfig> getExtendedGroupConfigs(Set<Field> fields, Base base) {
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();

        for (Field field : fields) {
            System.out.println("********** ObjectDetailsComposer, getExtendedGroupConfigs, name: " + field.getName());
            AttributeId attributeIdAn = field.getAnnotation(AttributeId.class);
            if (attributeIdAn != null) {
                String attributeId = attributeIdAn.value();
                System.out.println("********** ObjectDetailsComposer, getExtendedGroupConfigs, attributeId: " + attributeId);
                Optional<Attribute> attributeOp = attributeRepository.findById(attributeId);
                if (attributeOp.isPresent()) {
                    Attribute attribute = attributeOp.get();
                    String groupName = attribute.getAttributeGroup().getPublicName();
                    if (groupToFieldConfig.containsKey(groupName)) {
                        groupToFieldConfig.get(groupName).add(getExtendedFieldConfig(field, attribute, base));
                    } else {
                        List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                        fieldConfigs1.add(getExtendedFieldConfig(field, attribute, base));
                        groupToFieldConfig.put(groupName, fieldConfigs1);
                    }
                }
            }
        }

        System.out.println("********** ObjectDetailsComposer, getExtendedGroupConfigs, groupToFieldConfig: "+ groupToFieldConfig);
        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        System.out.println("********** ObjectDetailsComposer, getExtendedGroupConfigs, groupConfigs: "+ groupConfigs);

        return groupConfigs;
    }

    private FieldConfig getExtendedFieldConfig(Field field, Attribute attribute, Base base) {
        FieldConfig fieldConfig = getExtendedFieldConfigInstance(field, attribute, base);
        fieldConfig.setLabel(attribute.getPublicName());
        fieldConfig.setName(field.getName());
        fieldConfig.setMultiple(attribute.isMultiple());
        fieldConfig.setReadonly(attribute.isReadonly());
        fieldConfig.setRequired(attribute.isRequired());
        Object value = metadataHelper.getValue(base, base.getClass(), field);
        System.out.println("------------------value-------------------"+ value);
        fieldConfig.setValue(value);

        return fieldConfig;
    }

    private FieldConfig getExtendedFieldConfigInstance(Field field, Attribute attribute, Base base) {
        FieldConfig fieldConfig;

        if (FieldType.TEXT.value == attribute.getAttributeType()) {
            fieldConfig = new TextBoxFieldConfig();
        } else if (FieldType.NUMBER.value == attribute.getAttributeType()) {
            fieldConfig = new NumberFieldConfig();
        } else if (FieldType.REFERENCE.value == attribute.getAttributeType()) {
            fieldConfig = new ReferenceFieldConfig();
        } else if (FieldType.LIST.value == attribute.getAttributeType()) {
            List<ListFieldConfig.Options> options = new ArrayList<>();
            fieldConfig = new ListFieldConfig();
            if (field.isAnnotationPresent(BooleanAttr.class)) {
                ListFieldConfig.Options trueValue = new ListFieldConfig.Options(true, "Yes");
                ListFieldConfig.Options falseValue = new ListFieldConfig.Options(false, "No");
                options.add(trueValue);
                options.add(falseValue);
            } else {
                List<AttributeValue> attributeValues = attributeValueRepository.findByParentId(attribute.getId());
                System.out.println("********** ObjectDetailsComposer, getExtendedFieldConfig, attributeValues: " + attributeValues);
                attributeValues.stream()
                        .map(attributeValue -> {
                            ListFieldConfig.Options option = new ListFieldConfig.Options(attributeValue.getId(),
                                    attributeValue.getPublicName());
                            options.add(option);
                            return null;
                        });
            }
            System.out.println("********** ObjectDetailsComposer, getExtendedFieldConfig, options: "+ options);
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

    private List<GroupConfig> getBaseGroupConfigs(Set<Field> baseFields, Base base) {
        List<Field> visibleBaseFields = metadataHelper.getFieldsWithoutAnnotation(baseFields, Hidden.class);
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();
        for (Field field : visibleBaseFields) {
            GroupName groupNameAn = field.getAnnotation(GroupName.class);
            if (groupNameAn != null && !StringUtils.isEmpty(groupNameAn.value())) {
                if (groupToFieldConfig.containsKey(groupNameAn.value())) {
                    groupToFieldConfig.get(groupNameAn.value()).add(getBaseFieldConfig(field, base));
                } else {
                    List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                    fieldConfigs1.add(getBaseFieldConfig(field, base));
                    groupToFieldConfig.put(groupNameAn.value(), fieldConfigs1);
                }
            }
        }
        System.out.println("********** ObjectDetailsComposer, getBaseGroupConfigs, groupToFieldConfig: "+ groupToFieldConfig);
        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        System.out.println("********** ObjectDetailsComposer, getBaseGroupConfigs, groupConfigs: "+ groupConfigs);

        return groupConfigs;
    }

    private FieldConfig getBaseFieldConfig(Field field, Base base) {
        FieldConfig fieldConfig = getFieldConfigInstance(field);
        UIName uiNameAn = field.getAnnotation(UIName.class);
        if (uiNameAn != null) {
            String name = uiNameAn.value();
            System.out.println("********** ObjectDetailsComposer, getFieldConfigsByField, UI name: "+ name);
            fieldConfig.setLabel(name);
            fieldConfig.setName(field.getName());
            fieldConfig.setMultiple(false);
            fieldConfig.setReadonly(field.isAnnotationPresent(ReadOnly.class));
            fieldConfig.setRequired(field.isAnnotationPresent(Mandatory.class));
            Object value = metadataHelper.getValue(base, base.getClass(), field);
            System.out.println("------------------value-------------------"+ value);
            fieldConfig.setValue(value);
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
