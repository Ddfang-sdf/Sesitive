package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.PasswordDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface PasswordSensitive {
    Class<? extends Desensitizer<?, PasswordSensitive>> desensitizer() default PasswordDesensitizer.class;

    int startOffset() default 0;

    int endOffset() default 0;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<?>> condition() default PasswordSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<Object> {
        public AlwaysTrue() {
        }

        public boolean required(Object target) {
            return true;
        }
    }
}
