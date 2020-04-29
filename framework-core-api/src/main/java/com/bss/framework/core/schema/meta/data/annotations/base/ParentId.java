package com.bss.framework.core.schema.meta.data.annotations.base;

import com.bss.framework.core.schema.meta.data.annotations.AttributeId;
import com.bss.framework.core.schema.meta.data.annotations.Stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Stereotype
@AttributeId("-101")
public @interface ParentId {
}
