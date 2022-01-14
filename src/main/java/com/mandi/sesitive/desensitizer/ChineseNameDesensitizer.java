package com.mandi.sesitive.desensitizer;

import com.mandi.sesitive.annotation.ChineseNameSensitive;

public class ChineseNameDesensitizer extends AbstractCharSequenceDesensitizer<String, ChineseNameSensitive> {
    public ChineseNameDesensitizer() {
    }

    public String desensitize(String target, ChineseNameSensitive annotation) {
        return this.required(target, annotation.condition()) ? String.valueOf(this.desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }
}
