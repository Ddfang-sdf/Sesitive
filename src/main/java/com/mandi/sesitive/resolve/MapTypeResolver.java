package com.mandi.sesitive.resolve;


import com.mandi.sesitive.support.InstanceCreators;
import com.mandi.sesitive.util.ReflectionUtil;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.Map;
import java.util.stream.Collectors;

public class MapTypeResolver implements TypeResolver<Map<Object, Object>, AnnotatedParameterizedType> {
    public MapTypeResolver() {
    }

    public Map<Object, Object> resolve(Map<Object, Object> value, AnnotatedParameterizedType annotatedParameterizedType) {
        AnnotatedType[] annotatedActualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        return (Map)value.entrySet().parallelStream().collect(Collectors.collectingAndThen(Collectors.toMap((entry) -> {
            return TypeResolvers.resolve(entry.getKey(), annotatedActualTypeArguments[0]);
        }, (entry) -> {
            return TypeResolvers.resolve(entry.getValue(), annotatedActualTypeArguments[1]);
        }), (erased) -> {
            Map<Object, Object> map = (Map) InstanceCreators.getInstanceCreator(ReflectionUtil.getClass(value)).create();
            map.putAll(erased);
            return map;
        }));
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Map && annotatedType instanceof AnnotatedParameterizedType;
    }

    public int order() {
        return 1;
    }
}