package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.UsccDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface UsccSensitive {
    Class<? extends Desensitizer<? extends CharSequence, UsccSensitive>> desensitizer() default UsccDesensitizer.class;

    int startOffset() default 2;

    int endOffset() default 2;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<? extends CharSequence>> condition() default UsccSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<CharSequence> {
        public AlwaysTrue() {
        }

        public boolean required(CharSequence target) {
            return true;
        }
    }
}
