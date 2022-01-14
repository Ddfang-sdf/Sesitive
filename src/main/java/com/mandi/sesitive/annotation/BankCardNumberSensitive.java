package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.BankCardNumberDesensitizer;
import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface BankCardNumberSensitive {
    Class<? extends Desensitizer<?, BankCardNumberSensitive>> desensitizer() default BankCardNumberDesensitizer.class;

    int startOffset() default 0;

    int endOffset() default 4;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<?>> condition() default BankCardNumberSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<Object> {
        public AlwaysTrue() {
        }

        public boolean required(Object target) {
            return true;
        }
    }
}