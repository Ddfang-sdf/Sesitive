package com.mandi.sesitive.desensitizer;


import com.mandi.sesitive.support.InstanceCreators;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public abstract class AbstractCharSequenceDesensitizer<T extends CharSequence, A extends Annotation> implements Desensitizer<T, A> {
    private static final ConcurrentMap<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap();

    public AbstractCharSequenceDesensitizer() {
    }

    public boolean required(T target, Class<? extends Condition<?>> conditionClass) {
            return ((Condition) InstanceCreators.getInstanceCreator(conditionClass).create()).required(target);
    }

    public final char[] desensitize(T target, String regexp, int start, int end, char placeholder) {
        return this.isNotEmptyString(regexp) ? this.desensitize(target, regexp, placeholder) : this.desensitize(target, start, end, placeholder);
    }

    private char[] desensitize(T target, String regexp, char placeholder) {
        char[] chars = this.chars(target);
        Matcher matcher = ((Pattern)PATTERN_CACHE.computeIfAbsent(regexp, (s) -> {
            return Pattern.compile(regexp);
        })).matcher(target);

        while(matcher.find()) {
            if (this.isNotEmptyString(matcher.group())) {
                this.replace(chars, matcher.start(), matcher.end(), placeholder);
            }
        }

        return chars;
    }

    private char[] desensitize(T target, int start, int end, char placeholder) {
        this.check(start, end, target);
        char[] chars = this.chars(target);
        this.replace(chars, start, target.length() - end, placeholder);
        return chars;
    }

    private char[] chars(T target) {
        char[] chars = new char[target.length()];
        IntStream.range(0, target.length()).forEach((i) -> {
            chars[i] = target.charAt(i);
        });
        return chars;
    }

    private void replace(char[] chars, int start, int end, char placeholder) {
        while(start < end) {
            chars[start++] = placeholder;
        }

    }

    private void check(int startOffset, int endOffset, T target) {
        if (startOffset < 0 || endOffset < 0 || startOffset + endOffset > target.length()) {
            throw new IllegalArgumentException(String.format("startOffset: %s, endOffset: %s, target: %s", startOffset, endOffset, target));
        }
    }

    private boolean isNotEmptyString(String string) {
        return !"".equals(string);
    }
}