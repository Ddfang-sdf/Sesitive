package com.mandi.sesitive.desensitizer;


import com.mandi.sesitive.annotation.BankCardNumberSensitive;

public class BankCardNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, BankCardNumberSensitive> {
    public BankCardNumberDesensitizer() {
    }

    public String desensitize(String target, BankCardNumberSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}
