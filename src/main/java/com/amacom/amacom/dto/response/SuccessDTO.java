package com.amacom.amacom.dto.response;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessDTO extends ResponseDTO {

    /**
     * Create a { SuccessDTO} with a message only.
     * 
     * @param message the message
     */
    public SuccessDTO() {
        super(true, "Success");
    }

    /**
     * Create a { SuccessDTO} with a message only.
     * 
     * @param message the message
     */
    public SuccessDTO(String message) {
        super(true, message);
    }

    /**
     * Create a { SuccessDTO} with a message only.
     * 
     * @param message the message
     */
    public SuccessDTO(@Nullable Object data) {
        this(data, "Success");
    }

    /**
     * Create a { SuccessDTO} with a data and message.
     * 
     * @param data    the entity data
     * @param message the message
     */
    public SuccessDTO(@Nullable Object data, String message) {
        super(data, true, message);
    }

}
