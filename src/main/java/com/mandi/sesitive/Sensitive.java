package com.mandi.sesitive;


import com.mandi.sesitive.annotation.CascadeSensitive;
import com.mandi.sesitive.resolve.TypeResolvers;
import com.mandi.sesitive.support.TypeToken;

import java.util.Optional;

public final class Sensitive {
    private Sensitive() {
    }

    /**
     * 级联脱敏，该方法不会改变原对象。
     *
     * @param <T>    目标对象类型
     * @param target 目标对象
     * @return 脱敏后的新对象
     */
    public static <T> T desensitize(T target) {
        return desensitize(target, new TypeToken<@CascadeSensitive T>() {
        });
    }

    /**
     * 根据{@link TypeToken}脱敏对象，该方法不会改变原对象。
     * <p><b>注意：{@link TypeToken}必须在静态方法、静态代码块中初始化或者作为静态变量初始化，
     * 不能在实例方法、实例代码块中初始化同时也不能作为成员变量初始化。</b></p>
     *
     * @param target    目标对象
     * @param typeToken {@link TypeToken}
     * @param <T>       目标对象类型
     * @return 脱敏后的新对象
     */
    public static <T> T desensitize(T target, TypeToken<T> typeToken) {
        return Optional.ofNullable(target)
                .map(t -> typeToken)
                .map(TypeToken::getAnnotatedType)
                .map(annotatedType -> TypeResolvers.resolve(target, annotatedType))
                .orElse(target);
    }
}
