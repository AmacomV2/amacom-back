package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogBookDTO implements Serializable {

    private static final long serialVersionUID = -1132239458854764719L;

    private UUID id;

    private UUID personId;

    @NotNull(message = "Field cannot be null")
    private String name;

    private String  description;

    private Date createdAt;

    private Date updatedAt;

}
