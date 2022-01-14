package com.mandi.sesitive.support;
@FunctionalInterface
public interface InstanceCreator<T> {
    T create();
}
