package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.UsccSensitive;

public class UsccDesensitizer extends AbstractCharSequenceDesensitizer<String, UsccSensitive> {
    public UsccDesensitizer() {
    }

    public String desensitize(String target, UsccSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}

