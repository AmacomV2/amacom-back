package com.amacom.amacom.dto;

import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InstitutionServicePersonDTO implements Serializable {

    private static final long serialVersionUID = -817325155027218392L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idInstitutionService;

}
