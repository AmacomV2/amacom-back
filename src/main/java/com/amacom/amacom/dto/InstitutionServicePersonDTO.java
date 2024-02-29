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
public class InstitutionServicePersonDTO implements Serializable {

    private static final long serialVersionUID = -817325155027218392L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID personId;

    @NotNull(message = "Field cannot be null")
    private UUID idInstitutionService;

}
