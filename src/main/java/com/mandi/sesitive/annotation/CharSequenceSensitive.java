package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.StringDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface CharSequenceSensitive {
    Class<? extends Desensitizer<? extends CharSequence, CharSequenceSensitive>> desensitizer() default StringDesensitizer.class;

    int startOffset() default 0;

    int endOffset() default 0;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<? extends CharSequence>> condition() default CharSequenceSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<CharSequence> {
        public AlwaysTrue() {
        }

        public boolean required(CharSequence target) {
            return true;
        }
    }
}
