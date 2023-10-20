package com.amacom.amacom.dto;

import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.Services;
import com.amacom.amacom.model.auth.Usuario;
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
public class InstitutionServiceDTO implements Serializable {

    private static final long serialVersionUID = -4425238649579729561L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idUsuario;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idServices;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idInstitution;

}
