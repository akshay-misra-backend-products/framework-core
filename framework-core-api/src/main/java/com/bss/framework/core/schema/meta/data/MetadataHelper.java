package com.bss.framework.core.schema.meta.data;

import com.bss.framework.core.schema.model.Base;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MetadataHelper<T extends Base> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataHelper.class);
    
    private final Set<Field> baseFields = new HashSet<>();
    private Map<Class<T>, Set<Field>> fields = new ConcurrentHashMap();
    private Map<Field, Method> getterMethods = new ConcurrentHashMap();
    private Map<Field, Method> setterMethods = new ConcurrentHashMap();
    
    public Set<Field> getFields(Class<T> clazz) {
        if (!fields.containsKey(clazz)) {
            fields.put(clazz, collectFields(clazz, new Predicate<Field>() {
                @Override
                public boolean apply(@Nullable Field field) {
                    if (field == null) {
                        return false;
                    }

                    if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()))
                        return false;
                    
                    if(field.getDeclaredAnnotations() !=null  && field.getDeclaredAnnotations().length > 0)
                    {
                    	field.setAccessible(true);
                    	return true;
                    }
                    return false;
                }
            }));
        }
        return fields.get(clazz);
    }

    public Set<Field> getFields(Class<T> clazz, final Set<String> loadOnly) {
        Set<Field> fields = getFields(clazz);
        return Sets.filter(fields, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field != null && loadOnly.contains(field.getName());
            }
        });
    }

    public Method getGetterMethod(Class<? extends Base> clazz, Field field) {
        if (!getterMethods.containsKey(field)) {
            Method getterMethod = extractMethod(clazz, makeGetterName(field.getName(), field.getGenericType()));
            if (getterMethod != null) {
                getterMethods.put(field, getterMethod);
            }
        }
        return getterMethods.get(field);
    }

    public Method getSetterMethod(Class<? extends Base> clazz, Field field) {
        if (!setterMethods.containsKey(field)) {
            Method setterMethod = extractMethod(clazz,
                    makeSetterName(field.getName()), field.getType());
            if (setterMethod != null) {
                setterMethods.put(field, setterMethod);
            }
        }
        return setterMethods.get(field);
    }

    public Set<Field> getBaseFields() {
        if (baseFields.isEmpty()) {
            baseFields.addAll(getFields((Class<T>) Base.class,
                    ImmutableSet.of("id", "parentId", "objectTypeId", "name", "publicName",
                            "description", "version", "lastModifiedAt", "createdAt", "order")));
        }

        return baseFields;
    }

    public Set<Field> collectFields(final Class clazz, final Predicate<Field> fieldPredicate) {
        return collectFields(clazz, Sets.<Field>newHashSet(), fieldPredicate);
    }

    private Set<Field> collectFields(final Class clazz,
                                     final Set<Field> fields,
                                     final Predicate<Field> fieldPredicate) {
        if (clazz == null) {
            return fields;
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (fieldPredicate.apply(field)) {
                fields.add(field);
            }
        }

        if (Base.class == clazz) {
            return fields;
        }

        return collectFields(clazz.getSuperclass(), fields, fieldPredicate);
    }

    public String makeGetterName(String name, Type type) {
        boolean booleanType = Boolean.class.equals(type) || Boolean.TYPE.equals(type);
        return (booleanType ? "is" : "get") + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String makeSetterName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public Method extractMethod(Class clazz, String name, Class... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            LOGGER.warn("Method {} not found with parameters {} in class {}", name, parameterTypes, clazz);
        }
        return null;
    }

    public Object getValue(Object object, Class clazz, Field field) {
        Object value = null;
        Method method = getGetterMethod(clazz, field);
        try {
            value = method.invoke(object, (Object[]) null);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Object {} getterMethod {} returns {}", object, method.getName(), value);
            }
        } catch (Exception e) {
            LOGGER.error("Getter method {} invocation failed with error ",
                    method.getName(), e);
        }
        return value;
    }

    public void setValue(Object object, Class clazz, Field field, Object value) {
        Method method = getSetterMethod(clazz, field);
        try {
            method.invoke(object, new Object[]{value});
        } catch (Exception e) {
            LOGGER.warn("Setter method {} invocation with value {} failed with error ",
                    method.getName(), value, e);
        }
    }

    public boolean hasAnnotation(Field field, Class<? extends Annotation> annotation) {
        return field.isAnnotationPresent(annotation);
    }

    public boolean hasAnyAnnotation(Field field, List<Class<? extends Annotation>> annotations) {
        long count = annotations.stream().filter(annotation -> field.isAnnotationPresent(annotation)).count();
        return count > 0;
    }

    public List<Field> getFieldsWithoutAnnotation(Collection<Field> fields, Class<? extends Annotation> annotation) {
        List<Field> filteredFields = fields.stream()
                .filter(field -> !field.isAnnotationPresent(annotation))
                .collect(Collectors.toList());

        return filteredFields;
    }

    public List<Field> getFieldsWithoutAnnotations(Collection<Field> fields, List<Class<? extends Annotation>> annotations) {
        List<Field> filteredFields = fields.stream()
                .filter(field ->  !hasAnyAnnotation(field, annotations))
                .collect(Collectors.toList());

        return filteredFields;
    }

    public <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            String msg = "Exception occurred during creating new instance of class: " + clazz.getName();
            LOGGER.error(msg, e);
            throw new RuntimeException(msg, e);
        } catch (IllegalAccessException e) {
            String msg = "Exception occurred during creating new instance of class: " + clazz.getName();
            LOGGER.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public <T> boolean isAbstractType(Class<T> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }
}
