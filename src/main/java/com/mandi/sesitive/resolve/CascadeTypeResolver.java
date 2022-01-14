package com.mandi.sesitive.resolve;


import com.mandi.sesitive.annotation.CascadeSensitive;
import com.mandi.sesitive.support.InstanceCreators;
import com.mandi.sesitive.util.ReflectionUtil;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Modifier;

public class CascadeTypeResolver implements TypeResolver<Object, AnnotatedType> {
    public CascadeTypeResolver() {
    }

    public Object resolve(Object value, AnnotatedType annotatedType) {
        Class<?> clazz = value.getClass();
        Object newObject = InstanceCreators.getInstanceCreator(clazz).create();
        ReflectionUtil.listAllFields(clazz).parallelStream().forEach((field) -> {
            Object fieldValue;
            if (!Modifier.isFinal(field.getModifiers()) && (fieldValue = ReflectionUtil.getFieldValue(value, field)) != null) {
                ReflectionUtil.setFieldValue(newObject, field, TypeResolvers.resolve(fieldValue, field.getAnnotatedType()));
            }

        });
        return newObject;
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null && annotatedType.getDeclaredAnnotation(CascadeSensitive.class) != null;
    }

    public int order() {
        return 2147483647;
    }
}
