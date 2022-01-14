package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.IdCardNumberDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface IdCardNumberSensitive {
    Class<? extends Desensitizer<? extends CharSequence, IdCardNumberSensitive>> desensitizer() default IdCardNumberDesensitizer.class;

    int startOffset() default 6;

    int endOffset() default 4;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<? extends CharSequence>> condition() default IdCardNumberSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<CharSequence> {
        public AlwaysTrue() {
        }

        public boolean required(CharSequence target) {
            return true;
        }
    }
}
