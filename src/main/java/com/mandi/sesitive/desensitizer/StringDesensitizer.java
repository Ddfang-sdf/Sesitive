package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.CharSequenceSensitive;

public class StringDesensitizer extends AbstractCharSequenceDesensitizer<String, CharSequenceSensitive> {
    public StringDesensitizer() {
    }

    public String desensitize(String target, CharSequenceSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}