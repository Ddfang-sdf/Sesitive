package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.CharSequenceSensitive;

public class StringBufferDesensitizer extends AbstractCharSequenceDesensitizer<StringBuffer, CharSequenceSensitive> {
    public StringBufferDesensitizer() {
    }

    public StringBuffer desensitize(StringBuffer target, CharSequenceSensitive annotation) {
        return this.required(target, annotation.condition()) ? (new StringBuffer()).append(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}