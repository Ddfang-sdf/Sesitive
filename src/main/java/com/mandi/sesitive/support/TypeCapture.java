package com.mandi.sesitive.support;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class TypeCapture<T> {
    TypeCapture() {
    }

    AnnotatedType capture() {
        Class<?> clazz = this.getClass();
        Type superclass = clazz.getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            throw new IllegalArgumentException(String.format("%s必须是参数化类型", superclass));
        } else {
            return ((AnnotatedParameterizedType)clazz.getAnnotatedSuperclass()).getAnnotatedActualTypeArguments()[0];
        }
    }
}