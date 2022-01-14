package com.mandi.sesitive.resolve;

import com.mandi.sesitive.support.InstanceCreators;
import com.mandi.sesitive.util.ReflectionUtil;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionTypeResolver implements TypeResolver<Collection<Object>, AnnotatedParameterizedType> {
    public CollectionTypeResolver() {
    }

    public Collection<Object> resolve(Collection<Object> value, AnnotatedParameterizedType annotatedParameterizedType) {
        return (Collection)value.parallelStream().map((o) -> {
            return TypeResolvers.resolve(o, annotatedParameterizedType.getAnnotatedActualTypeArguments()[0]);
        }).collect(Collectors.collectingAndThen(Collectors.toList(), (erased) -> {
            Collection<Object> collection = (Collection) InstanceCreators.getInstanceCreator(ReflectionUtil.getClass(value)).create();
            collection.addAll(erased);
            return collection;
        }));
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Collection && annotatedType instanceof AnnotatedParameterizedType;
    }

    public int order() {
        return 0;
    }
}
