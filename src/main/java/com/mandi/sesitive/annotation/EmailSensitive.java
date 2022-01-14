package com.mandi.sesitive.annotation;


import com.mandi.sesitive.desensitizer.Condition;
import com.mandi.sesitive.desensitizer.Desensitizer;
import com.mandi.sesitive.desensitizer.EmailDesensitizer;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface EmailSensitive {
    Class<? extends Desensitizer<? extends CharSequence, EmailSensitive>> desensitizer() default EmailDesensitizer.class;

    int startOffset() default 0;

    int endOffset() default 0;

    String regexp() default "(?<=.).*(?=@)";

    char placeholder() default '*';

    Class<? extends Condition<? extends CharSequence>> condition() default EmailSensitive.AlwaysTrue.class;

    public static class AlwaysTrue implements Condition<CharSequence> {
        public AlwaysTrue() {
        }

        public boolean required(CharSequence target) {
            return true;
        }
    }
}
