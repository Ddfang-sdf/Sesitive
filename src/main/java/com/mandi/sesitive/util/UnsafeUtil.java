package com.mandi.sesitive.util;

import com.mandi.sesitive.exception.DesensitizationException;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class UnsafeUtil {
    private static final Unsafe UNSAFE;

    private UnsafeUtil() {
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            //绕过构造方法、初始化代码来创建对象
            return (T) UNSAFE.allocateInstance(clazz);
        } catch (InstantiationException var2) {
            throw new DesensitizationException(String.format("实例化%s失败", clazz), var2);
        }
    }

    static {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe)f.get((Object)null);
        } catch (Exception var2) {
            throw new DesensitizationException(String.format("初始化%s失败", Unsafe.class), var2);
        }
    }
}