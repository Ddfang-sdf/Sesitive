package com.mandi.sesitive.desensitizer;


import java.lang.annotation.Annotation;

public interface Desensitizer<T, A extends Annotation> {
    T desensitize(T var1, A var2);
}