package com.gbss.framework.core.web.impl.composers;

import com.gbss.framework.core.api.service.api.AttributeSchemaService;
import com.gbss.framework.core.api.utils.EntityBuilder;
import com.gbss.framework.core.impl.factory.ObjectTypeRepositoryMapperFactory;
import com.gbss.framework.core.impl.meta.data.MetadataHelper;
import com.gbss.framework.core.impl.repositories.AttributeRepository;
import com.gbss.framework.core.impl.repositories.AttributeValueRepository;
import com.gbss.framework.core.impl.repositories.ObjectTypeRepository;
import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.GroupName;
import com.gbss.framework.core.meta.annotations.Hidden;
import com.gbss.framework.core.meta.annotations.Mandatory;
import com.gbss.framework.core.meta.annotations.ReadOnly;
import com.gbss.framework.core.meta.annotations.UIName;
import com.gbss.framework.core.meta.annotations.base.Description;
import com.gbss.framework.core.meta.annotations.base.Name;
import com.gbss.framework.core.meta.annotations.base.ObjectTypeId;
import com.gbss.framework.core.meta.annotations.base.OrderNumber;
import com.gbss.framework.core.meta.annotations.base.PublicName;
import com.gbss.framework.core.meta.annotations.base.VersionNumber;
import com.gbss.framework.core.model.constants.SystemConstants;
import com.gbss.framework.core.model.entities.Attribute;
import com.gbss.framework.core.model.entities.AttributeValue;
import com.gbss.framework.core.model.entities.Base;
import com.gbss.framework.core.model.entities.ObjectType;
import com.gbss.framework.core.web.api.composers.Composer;
import com.gbss.framework.core.web.model.CompositeTableConfig;
import com.gbss.framework.core.web.model.DynamicTableConfig;
import com.gbss.framework.core.web.model.FieldConfig;
import com.gbss.framework.core.web.model.GroupConfig;
import com.gbss.framework.core.web.model.ObjectDetailsConfig;
import com.gbss.framework.core.web.model.ObjectLayoutWrapper;
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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ejb.ObjectNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ObjectDetailsComposer<T extends ObjectLayoutWrapper> implements Composer<T> {

    @Autowired
    ObjectTypeRepositoryMapperFactory objectTypeRepositoryFactory;

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    @Autowired
    AttributeSchemaService attributeSchemaService;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @Autowired
    MetadataHelper metadataHelper;

    @Autowired
    DynamicTableComposer dynamicTableComposer;


    @Autowired
    EntityBuilder entityBuilder;

    @Override
    public T compose(String objectTypeId, String objectId) throws ObjectNotFoundException {
        ObjectLayoutWrapper objectLayoutWrapper = new ObjectLayoutWrapper();
        ObjectDetailsConfig detail = new ObjectDetailsConfig();
        objectLayoutWrapper.setDetail(detail);

        Base base = entityBuilder.getObjectById(objectTypeId, objectId);
        System.out.println("********** ObjectDetailsComposer, repository base fields: "+ base);
        detail.setId(objectId);
        detail.setObjectName(base.getPublicName() == null ? base.getName() : base.getPublicName());
        detail.setUpdateAPI("/application/api/"+objectTypeId+"/update");
        detail.setEditable(true);
        objectLayoutWrapper.setObjectName(base.getPublicName() == null ? base.getName() : base.getPublicName());

        Set<Field> fields = metadataHelper.getFields(base.getClass());
        Set<Field> baseFields = metadataHelper.getBaseFields();
        List<GroupConfig> groupConfigs = getBaseGroupConfigs(baseFields, base);
        fields.removeAll(baseFields);
        groupConfigs.addAll(getExtendedGroupConfigs(fields, base));
        detail.setGroups(groupConfigs);

        objectLayoutWrapper.setChildren(composeChildTableConfigs(objectTypeId, objectId));

        return (T) objectLayoutWrapper;
    }

    private CompositeTableConfig composeChildTableConfigs(String objectTypeId, String objectId) {
        System.out.println("********** ObjectDetailsComposer, composeChildTableConfigs, objectTypeId: "+ objectTypeId+", objectId: "+objectId);
        CompositeTableConfig compositeTableConfig = new CompositeTableConfig();
        List<String> childObjectTypes = new ArrayList<>();
        ObjectType currentOT = objectTypeRepository.findById(objectTypeId).get();
        System.out.println("********** ObjectDetailsComposer, composeChildTableConfigs, currentOT: "+currentOT);
        List<DynamicTableConfig> tables = new ArrayList<>();
        if (currentOT.isSameTypeChildren() && !SystemConstants.ObjectTypes.OBJECT_TYPE.equals(objectId)) {
            System.out.println("********** ObjectDetailsComposer, composeChildTableConfigs, isSameTypeChildren ... ");
            DynamicTableConfig dynamicTableConfig = dynamicTableComposer.compose(currentOT.getId(), objectId);
            tables.add(dynamicTableConfig);
        }

        List<ObjectType> childOTs = objectTypeRepository.findByParentId(objectTypeId);
        System.out.println("********** ########## ObjectDetailsComposer, composeChildTableConfigs, childOTs: "+childOTs);
        if (CollectionUtils.isNotEmpty(childOTs)) {
            System.out.println("********** ########## ObjectDetailsComposer, composeChildTableConfigs, childOTs, isNotEmpty");
            childObjectTypes = childOTs.stream().map(ObjectType::getId).collect(Collectors.toList());
        }
        System.out.println("********** ########## ObjectDetailsComposer, composeChildTableConfigs, childObjectTypes: "+childObjectTypes);
        for (String childOT : childObjectTypes) {
            DynamicTableConfig dynamicTableConfig = dynamicTableComposer.compose(childOT, objectId);
            tables.add(dynamicTableConfig);
        }

        compositeTableConfig.setTables(tables);

        return compositeTableConfig;
    }

    private List<GroupConfig> getExtendedGroupConfigs(Set<Field> fields, Base base) {
        System.out.println("********** ########## ObjectDetailsComposer, getExtendedGroupConfigs, fields: "+ fields +
                ", base: " + base);
        List<GroupConfig> groupConfigs = new ArrayList<>();
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();

        for (Field field : fields) {
            AttributeId attributeIdAn = field.getAnnotation(AttributeId.class);
            if (attributeIdAn != null) {
                String attributeId = attributeIdAn.value();
                if (SystemConstants.Attributes.DYNAMIC_PARAMETERS.equals(attributeId)) {
                    groupToFieldConfig.putAll(getGroupToFieldConfigs(field, base));
                } else {
                    Optional<Attribute> attributeOp = attributeRepository.findById(attributeId);
                    if (attributeOp.isPresent()) {
                        Attribute attribute = attributeOp.get();
                        String groupName = attribute.getAttributeGroup().getPublicName();
                        System.out.println("********** ########## ObjectDetailsComposer, getExtendedGroupConfigs, " +
                                "groupName: " + groupName);
                        Object value = metadataHelper.getValue(base, base.getClass(), field);
                        if (groupToFieldConfig.containsKey(groupName)) {
                            groupToFieldConfig.get(groupName).add(getExtendedFieldConfig(attribute, value, base));
                        } else {
                            List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                            fieldConfigs1.add(getExtendedFieldConfig(attribute, value, base));
                            groupToFieldConfig.put(groupName, fieldConfigs1);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        return groupConfigs;
    }

    private Map<String, List<FieldConfig>> getGroupToFieldConfigs(Field field, Base base) {
        Map<String, List<FieldConfig>> groupToFieldConfig = new HashMap<>();
        Object value = metadataHelper.getValue(base, base.getClass(), field);
        System.out.println("********** ########## ObjectDetailsComposer, getGroupToFieldConfigs, value: "+ value);
        if (value != null) {
            Map<String, Object> extendedParameters = (Map<String, Object>) value;
            for (Map.Entry<String, Object> entry : extendedParameters.entrySet()) {
                Optional<Attribute> attributeOp = attributeRepository.findById(entry.getKey());
                if (attributeOp.isPresent()) {
                    Attribute attribute = attributeOp.get();
                    String groupName = attribute.getAttributeGroup().getPublicName();
                    System.out.println("********** ########## ObjectDetailsComposer, getGroupToFieldConfigs, " +
                            "groupName: " + groupName);
                    if (groupToFieldConfig.containsKey(groupName)) {
                        groupToFieldConfig.get(groupName).add(getExtendedFieldConfig(attribute, entry.getValue(), base));
                    } else {
                        List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                        fieldConfigs1.add(getExtendedFieldConfig(attribute, entry.getValue(), base));
                        groupToFieldConfig.put(groupName, fieldConfigs1);
                    }
                }
            }

            ObjectType objectType = attributeSchemaService.getObjectTypeById(base.getObjectTypeId());
            if (objectType.getAttributes() != null) {
                for (Attribute attribute : objectType.getAttributes()) {
                    if (extendedParameters.keySet().contains(attribute.getId())) {
                        continue;
                    }
                    String groupName = attribute.getAttributeGroup().getPublicName();
                    System.out.println("********** ########## ObjectDetailsComposer, getGroupToFieldConfigs, " +
                            "groupName: " + groupName);
                    if (groupToFieldConfig.containsKey(groupName)) {
                        groupToFieldConfig.get(groupName).add(getExtendedFieldConfigInstance(attribute));
                    } else {
                        List<FieldConfig> fieldConfigs1 = new ArrayList<>();
                        fieldConfigs1.add(getExtendedFieldConfigInstance(attribute));
                        groupToFieldConfig.put(groupName, fieldConfigs1);
                    }
                }
            }
        }

        System.out.println("********** ########## ObjectDetailsComposer, getGroupToFieldConfigs, groupToFieldConfig: "
                + groupToFieldConfig);

        return groupToFieldConfig;
    }

    private FieldConfig getExtendedFieldConfig(Attribute attribute, Object value, Base base) {
        System.out.println("********** ########## ObjectDetailsComposer, getExtendedGroupConfigs, "
                + "attribute: " + attribute
                + ", value: " + value
                + ", base: " + base);
        FieldConfig fieldConfig = getExtendedFieldConfigInstance(attribute);
        System.out.println("********** ########## ObjectDetailsComposer, getExtendedGroupConfigs, fieldConfig: "+
                fieldConfig);

        System.out.println("********** ########## ObjectDetailsComposer, getExtendedGroupConfigs, value: "+ value);
        if (FieldType.REFERENCE_ID.value == attribute.getAttributeType() && value != null) {
            if (attribute.getReferenceToObjectType() != null) {
                MongoRepository repository = objectTypeRepositoryFactory.
                        getBean(attribute.getReferenceToObjectType());
                Base refIdValue = (Base) repository.findById(value).get();
                fieldConfig.setValue(refIdValue);
            } else {
                fieldConfig.setValue(value);
            }
        } else {
            fieldConfig.setValue(value);
        }

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
            String loadAPI = "";
            if (attribute.getReferenceToObjectType() == null &&
                    SystemConstants.Attributes.REFERENCE_TO_OBJECT_TYPE.equals(attribute.getId())) {
                loadAPI = "/application/api/" + SystemConstants.ObjectTypes.OBJECT_TYPE +"/load/all";
            } else {
                loadAPI = "/application/api/" + attribute.getReferenceToObjectType() + "/load/all";
            }
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
        } else if (FieldType.LIST.value == attribute.getAttributeType()) {
            List<ListFieldConfig.Options> options = new ArrayList<>();
            fieldConfig = new ListFieldConfig();
            if (SystemConstants.Attributes.ATTRIBUTE_TYPE.equals(attribute.getId())) {
                options.addAll(getAttributeTypeOptions());
            } else {
                List<AttributeValue> attributeValues = attributeValueRepository.findByParentId(attribute.getId());
                attributeValues.stream()
                        .map(attributeValue -> {
                            ListFieldConfig.Options option = new ListFieldConfig.Options(attributeValue.getId(),
                                    attributeValue.getPublicName());
                            options.add(option);
                            return null;
                        });
            }
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

        fieldConfig.setLabel(attribute.getPublicName());
        fieldConfig.setName(attribute.getId());
        fieldConfig.setMultiple(attribute.isMultiple());
        fieldConfig.setReadonly(attribute.isReadonly());
        fieldConfig.setRequired(attribute.isRequired());

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
        for (Map.Entry<String, List<FieldConfig>> entry : groupToFieldConfig.entrySet()) {
            GroupConfig groupConfig = new GroupConfig();
            groupConfig.setGroupName(entry.getKey());
            groupConfig.setFields(entry.getValue());
            groupConfigs.add(groupConfig);
        }

        return groupConfigs;
    }

    private FieldConfig getBaseFieldConfig(Field field, Base base) {
        FieldConfig fieldConfig = getFieldConfigInstance(field);
        UIName uiNameAn = field.getAnnotation(UIName.class);
        if (uiNameAn != null) {
            String name = uiNameAn.value();
            fieldConfig.setLabel(name);
            fieldConfig.setName(field.getName());
            fieldConfig.setMultiple(false);
            fieldConfig.setReadonly(field.isAnnotationPresent(ReadOnly.class));
            fieldConfig.setRequired(field.isAnnotationPresent(Mandatory.class));
            Object value = metadataHelper.getValue(base, base.getClass(), field);
            if (metadataHelper.hasAnnotation(field, ObjectTypeId.class) && value != null) {
                Optional<ObjectType> objectType = objectTypeRepository.findById(value.toString());
                fieldConfig.setValue(objectType.get());
            } else {
                fieldConfig.setValue(value);
            }
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
        } else if (metadataHelper.hasAnnotation(field, ObjectTypeId.class)) {
            fieldConfig = new ReferenceFieldConfig();
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
            fieldConfig.setReadonly(true);
        } /*else if (metadataHelper.hasAnnotation(field, ParentId.class)) {
            fieldConfig = new ReferenceFieldConfig();
            ((ReferenceFieldConfig) fieldConfig).setRefIdAttr(true);
            String loadAPI = "/application/api/" + SystemConstants.ObjectTypes.OBJECT_TYPE + "/load/all";
            ((ReferenceFieldConfig) fieldConfig).setLoadAPI(loadAPI);
        }*/ else {
            fieldConfig = new TextBoxFieldConfig();
        }

        return fieldConfig;
    }
}
