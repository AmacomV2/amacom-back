package com.amacom.amacom.dto.response;

import org.springframework.lang.Nullable;

import com.amacom.amacom.util.ErrorCodes;

public class ErrorDTO extends ResponseDTO {

    /**
     * Create a { ErrorDTO} with a message only.
     * 
     */
    public ErrorDTO() {
        super(null, false, "Error.");
    }

    /**
     * Create a { ErrorDTO} with a message only.
     * 
     * @param message the message
     */
    public ErrorDTO(String message) {
        super(null, false, message);
    }

    /**
     * Create a { ErrorDTO} with a message only.
     * 
     * @param message the message
     */
    public ErrorDTO(Exception e) {
        super(e, false, e.getLocalizedMessage());
    }

    /**
     * Create a { ErrorDTO} with a message only.
     * 
     * @param message the message
     */
    public ErrorDTO(ErrorCodes errorData) {
        super(errorData, false, errorData.getReasonPhrase());
    }

    /**
     * Create a { ErrorDTO} with a data and message.
     * 
     * @param data    the entity data
     * @param message the message
     */
    public ErrorDTO(@Nullable ErrorCodes errorData, String message) {
        super(errorData, message);
    }

}
