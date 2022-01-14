package com.mandi.sesitive.resolve;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;
import java.util.Arrays;
import java.util.stream.Stream;

public class WildcardTypeResolver implements TypeResolver<Object, AnnotatedWildcardType> {
    public WildcardTypeResolver() {
    }

    public Object resolve(Object value, AnnotatedWildcardType annotatedWildcardType) {
        return Stream.of(annotatedWildcardType.getAnnotatedUpperBounds(), annotatedWildcardType.getAnnotatedLowerBounds()).flatMap(Arrays::stream).reduce(value, TypeResolvers::resolve, (u1, u2) -> {
            return null;
        });
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null && annotatedType instanceof AnnotatedWildcardType;
    }

    public int order() {
        return -2147483647;
    }
}

