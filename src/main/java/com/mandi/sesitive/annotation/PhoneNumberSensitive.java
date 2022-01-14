package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.PhoneNumberDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface PhoneNumberSensitive {
    Class<? extends Desensitizer<?, PhoneNumberSensitive>> desensitizer() default PhoneNumberDesensitizer.class;

    int startOffset() default 3;

    int endOffset() default 4;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<?>> condition() default PhoneNumberSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<Object> {
        public AlwaysTrue() {
        }

        public boolean required(Object target) {
            return true;
        }
    }
}
