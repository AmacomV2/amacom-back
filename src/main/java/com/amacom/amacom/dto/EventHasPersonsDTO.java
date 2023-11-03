package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventHasPersonsDTO implements Serializable {

    private static final long serialVersionUID = 3445418192544864166L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID personId;

    private String personName;

    @NotNull(message = "Field cannot be null")
    private UUID idEvent;

    private String tituloEvent;

}
