package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.IdCardNumberSensitive;

public class IdCardNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, IdCardNumberSensitive> {
    public IdCardNumberDesensitizer() {
    }

    public String desensitize(String target, IdCardNumberSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}
