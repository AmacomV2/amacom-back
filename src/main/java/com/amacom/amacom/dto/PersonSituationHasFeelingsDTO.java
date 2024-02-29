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
public class PersonSituationHasFeelingsDTO implements Serializable {

    private static final long serialVersionUID = 4738300135194531468L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID personSituationId;

    @NotNull(message = "Field cannot be null")
    private UUID idFeelings;

    private Boolean priority;

}
