package com.mandi.sesitive.resolve;


import com.mandi.sesitive.annotation.SensitiveAnnotation;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.exception.DesensitizationException;
import com.mandi.sesitive.support.InstanceCreators;
import com.mandi.sesitive.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ObjectTypeResolver implements TypeResolver<Object, AnnotatedType> {
    private static final ConcurrentMap<Class<Desensitizer<Object, Annotation>>, Desensitizer<Object, Annotation>> DESENSITIZER_CACHE = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<? extends Annotation>, Method> DESENSITIZER_METHOD_CACHE = new ConcurrentHashMap();

    public ObjectTypeResolver() {
    }

    public Object resolve(Object value, AnnotatedType annotatedType) {
        return Arrays.stream(annotatedType.getDeclaredAnnotations()).filter((annotation) -> {
            return annotation.annotationType().isAnnotationPresent(SensitiveAnnotation.class);
        }).findFirst().map((sensitiveAnnotation) -> {
            return this.getDesensitizer(sensitiveAnnotation).desensitize(value, sensitiveAnnotation);
        }).orElse(value);
    }

    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null;
    }

    public int order() {
        return 2147483646;
    }

    private Desensitizer<Object, Annotation> getDesensitizer(Annotation annotation) {
        try {
            Method desensitizerMethod = (Method)DESENSITIZER_METHOD_CACHE.computeIfAbsent(annotation.annotationType(), (annotationClass) -> {
                return ReflectionUtil.getDeclaredMethod(annotationClass, "desensitizer", new Class[0]);
            });
            return (Desensitizer)DESENSITIZER_CACHE.computeIfAbsent(ReflectionUtil.invokeMethod(annotation, desensitizerMethod, new Object[0]), (clazz) -> {
                return (Desensitizer) InstanceCreators.getInstanceCreator(clazz).create();
            });
        } catch (Exception var3) {
            throw new DesensitizationException(String.format("实例化敏感注解%s的脱敏器失败。", annotation.annotationType()), var3);
        }
    }
}
