package com.amacom.amacom.dto.response;

import java.io.Serializable;

import org.springframework.lang.Nullable;

import com.amacom.amacom.util.ErrorCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO implements Serializable {
    @Nullable
    private final boolean ok;

    @Nullable
    private final String message;

    @Nullable
    private final Object data;

    /**
     * Create a {@code ResponseDTO} with a message code only.
     * 
     * @param message the message code
     */
    public ResponseDTO() {
        this(null, false, null);
    }

    /**
     * Create a {@code ResponseDTO} with a message code only.
     * 
     * @param message the message code
     */
    public ResponseDTO(String message) {
        this(null, false, message);
    }

    /**
     * Create a {@code ResponseDTO} with a message code only.
     * 
     * @param message the message code
     */
    public ResponseDTO(@Nullable Object data) {
        this(data, false, data.toString());
    }

    /**
     * Create a {@code ResponseDTO} with a data and message code.
     * 
     * @param data    the entity data
     * @param message the message code
     */
    public ResponseDTO(@Nullable Object data, String message) {
        this(data, false, message);
    }

    /**
     * Create a {@code ResponseDTO} with a data and message code.
     * 
     * @param data    the entity data
     * @param message the message code
     */
    public ResponseDTO(@Nullable ErrorCodes data, @Nullable String message) {
        this(data, false, message == null ? data.getReasonPhrase() : message);
    }

    /**
     * Create a {@code ResponseDTO} with ok and a message code.
     * 
     * @param ok      the entity ok
     * @param message the message code
     */
    public ResponseDTO(boolean ok, String message) {
        this(null, ok, message);
    }

    /**
     * Create a new {@code ResponseDTO} with the given data and headers.
     * 
     * @param data    the entity data
     * @param headers the entity message
     * @param ok      the entity ok
     */
    public ResponseDTO(@Nullable Object data, boolean ok, @Nullable String message) {
        this.data = data;
        this.message = message;
        this.ok = ok;
    }

    @Override
    public String toString() {
        return this.message + ' ' + this.ok;
    }
}
