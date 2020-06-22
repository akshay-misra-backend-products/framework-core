package com.gbss.framework.core.meta.annotations.base;

import com.gbss.framework.core.meta.annotations.AttributeId;
import com.gbss.framework.core.meta.annotations.Stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Stereotype
@AttributeId("-4")
public @interface OrderNumber {
}
