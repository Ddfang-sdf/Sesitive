package com.mandi.sesitive.resolve;

import com.mandi.sesitive.util.ReflectionUtil;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayTypeResolver implements TypeResolver<Object[], AnnotatedArrayType> {
    public ArrayTypeResolver() {
    }

    public Object[] resolve(Object[] value, AnnotatedArrayType annotatedArrayType) {
        return ((Stream) Arrays.stream(value).parallel()).map((o) -> {
            return TypeResolvers.resolve(o, annotatedArrayType.getAnnotatedGenericComponentType());
        }).toArray((length) -> {
            return ReflectionUtil.newArray(value.getClass().getComponentType(), length);
        });
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Object[] && annotatedType instanceof AnnotatedArrayType;
    }

    public int order() {
        return 2;
    }
}

