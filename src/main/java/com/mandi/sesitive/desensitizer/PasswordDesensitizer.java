package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.PasswordSensitive;

public class PasswordDesensitizer extends AbstractCharSequenceDesensitizer<String, PasswordSensitive> {
    public PasswordDesensitizer() {
    }

    public String desensitize(String target, PasswordSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}