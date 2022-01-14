package com.mandi.sesitive.resolve;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.util.Arrays;

public class TypeVariableResolver implements TypeResolver<Object, AnnotatedTypeVariable> {
    public TypeVariableResolver() {
    }

    public Object resolve(Object value, AnnotatedTypeVariable annotatedTypeVariable) {
        return Arrays.stream(annotatedTypeVariable.getAnnotatedBounds()).reduce(value, TypeResolvers::resolve, (u1, u2) -> {
            return null;
        });
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null && annotatedType instanceof AnnotatedTypeVariable;
    }

    public int order() {
        return -2147483648;
    }
}
