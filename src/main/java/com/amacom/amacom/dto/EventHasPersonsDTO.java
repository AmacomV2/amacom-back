package com.amacom.amacom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EventHasPersonsDTO implements Serializable {

    private static final long serialVersionUID = 3445418192544864166L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idEvento;

}
