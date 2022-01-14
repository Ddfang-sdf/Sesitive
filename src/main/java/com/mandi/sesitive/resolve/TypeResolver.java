package com.mandi.sesitive.resolve;


import java.lang.reflect.AnnotatedType;

public interface TypeResolver<T, AT extends AnnotatedType> extends Sortable, Comparable<TypeResolver<?, ? extends AnnotatedType>> {
    T resolve(T var1, AT var2);

    boolean support(Object var1, AnnotatedType var2);

    default int compareTo(TypeResolver<?, ? extends AnnotatedType> typeResolver) {
        return Integer.compare(this.order(), typeResolver.order());
    }
}