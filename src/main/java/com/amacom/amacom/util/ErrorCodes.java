package com.amacom.amacom.util;

import org.springframework.lang.Nullable;

public enum ErrorCodes {

    UNIQUE_VALUES_REQUIRED(100, "Non unique values detected, try changing them."),

    ERROR_CREATING_RECORD(102, "Error on saving record into DB.");

    private static final ErrorCodes[] VALUES;

    static {
        VALUES = values();
    }

    private final int value;

    private final String reasonPhrase;

    ErrorCodes(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return the integer value of this error code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this error code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * Return a string representation of this error code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }

    /**
     * Return the {@code ErrorCodes} enum constant with the specified numeric value.
     * 
     * @param errorCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the
     *                                  specified numeric value
     */
    public static ErrorCodes valueOf(int errorCode) {
        ErrorCodes error = resolve(errorCode);
        if (error == null) {
            throw new IllegalArgumentException("No matching constant for [" + errorCode + "]");
        }
        return error;
    }

    /**
     * Resolve the given error code to an {@code ErrorCodes}, if possible.
     * 
     * @param errorCode error code (potentially non-standard)
     * @return the corresponding {@code ErrorCodes}, or {@code null} if not found
     * @since 5.0
     */
    @Nullable
    public static ErrorCodes resolve(int errorCode) {
        // Use cached VALUES instead of values() to prevent array allocation.
        for (ErrorCodes error : VALUES) {
            if (error.value == errorCode) {
                return error;
            }
        }
        return null;
    }
}
