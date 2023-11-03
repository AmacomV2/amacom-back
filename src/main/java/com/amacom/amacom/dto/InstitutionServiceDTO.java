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
public class InstitutionServiceDTO implements Serializable {

    private static final long serialVersionUID = -4425238649579729561L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID userId;

    @NotNull(message = "Field cannot be null")
    private UUID idServices;

    @NotNull(message = "Field cannot be null")
    private UUID idInstitution;

}
