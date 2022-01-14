package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.PhoneNumberSensitive;

public class PhoneNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, PhoneNumberSensitive> {
    public PhoneNumberDesensitizer() {
    }

    public String desensitize(String target, PhoneNumberSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}
