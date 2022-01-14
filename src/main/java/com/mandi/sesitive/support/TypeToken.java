package com.mandi.sesitive.support;


import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class TypeToken<T> extends TypeCapture<T> {
    private final Type type;
    private final AnnotatedType annotatedType;

    protected TypeToken() {
        this.annotatedType = this.capture();
        this.type = this.annotatedType.getType();
    }

    private TypeToken(AnnotatedType annotatedType) {
        this.annotatedType = annotatedType;
        this.type = annotatedType.getType();
    }

    public static <T> TypeToken<T> of(AnnotatedType annotatedType) {
        return new TypeToken<T>(annotatedType) {
        };
    }

    public final Type getType() {
        return this.type;
    }

    public final AnnotatedType getAnnotatedType() {
        return this.annotatedType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            TypeToken<?> typeToken = (TypeToken)o;
            return this.type.equals(typeToken.type) && this.annotatedType.equals(typeToken.annotatedType);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type, this.annotatedType});
    }

    public String toString() {
        return (new StringJoiner(", ", TypeToken.class.getSimpleName() + "[", "]")).add("type=" + this.type).add("annotatedType=" + this.annotatedType).toString();
    }
}
