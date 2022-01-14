package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.EmailSensitive;

public class EmailDesensitizer extends AbstractCharSequenceDesensitizer<String, EmailSensitive> {
    public EmailDesensitizer() {
    }

    public String desensitize(String target, EmailSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}