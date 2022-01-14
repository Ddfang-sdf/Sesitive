package com.mandi.sesitive.exception;

import java.util.function.Supplier;

public class DesensitizationException extends RuntimeException implements Supplier<DesensitizationException> {
    public DesensitizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DesensitizationException(String message) {
        super(message);
    }

    public DesensitizationException get() {
        return this;
    }
}
