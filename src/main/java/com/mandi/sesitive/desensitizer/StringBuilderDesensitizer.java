package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.CharSequenceSensitive;

public class StringBuilderDesensitizer extends AbstractCharSequenceDesensitizer<StringBuilder, CharSequenceSensitive> {
    public StringBuilderDesensitizer() {
    }

    public StringBuilder desensitize(StringBuilder target, CharSequenceSensitive annotation) {
        return this.required(target, annotation.condition()) ? (new StringBuilder()).append(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}