package com.mandi.sesitive.util;


import com.mandi.sesitive.exception.DesensitizationException;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static List<Field> listAllFields(Class<?> targetClass) {
        return (List)Optional.ofNullable(targetClass).filter((clazz) -> {
            return clazz != Object.class;
        }).map((clazz) -> {
            List<Field> fields = (List)Stream.of(clazz.getDeclaredFields()).collect(Collectors.toList());
            fields.addAll(listAllFields(clazz.getSuperclass()));
            return fields;
        }).orElseGet(ArrayList::new);
    }

    public static <T> Constructor<T> getDeclaredConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }

            return declaredConstructor;
        } catch (NoSuchMethodException var3) {
            return null;
        }
    }

    public static <T> Class<T> getClass(T value) {
        return (Class<T>) value.getClass();
    }

    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException var4) {
            throw new DesensitizationException(String.format("获取%s的方法%s%s失败。", clazz, name, Arrays.toString(parameterTypes)), var4);
        }
    }

    public static Object getFieldValue(Object target, Field field) {
        try {
            if (field.isAccessible()) {
                return field.get(target);
            } else {
                field.setAccessible(true);
                return field.get(target);
            }
        } catch (Exception var3) {
            throw new DesensitizationException(String.format("获取%s的域%s失败。", target.getClass(), field.getName()), var3);
        }
    }

    public static void setFieldValue(Object target, Field field, Object newValue) {
        try {
            if (field.isAccessible()) {
                field.set(target, newValue);
            } else {
                field.setAccessible(true);
                field.set(target, newValue);
            }
        } catch (Exception var4) {
            throw new DesensitizationException(String.format("%s的域%s赋值失败。", target.getClass(), field.getName()), var4);
        }
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (Exception var3) {
            throw new DesensitizationException(String.format("构造器%s%s实例化失败", constructor, Arrays.toString(args)), var3);
        }
    }

    public static <T> T invokeMethod(Object target, Method targetMethod, Object... args) {
        try {
            return (T) targetMethod.invoke(target, args);
        } catch (Exception var4) {
            throw new DesensitizationException(String.format("调用目标对象%s上的方法%s%s失败", target, targetMethod, Arrays.toString(args)), var4);
        }
    }

    public static <T> T[] newArray(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length);
    }
}
