package com.amacom.amacom.dto;

import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;


    private Long idRol;


    @NotNull(message = "Campo no puede ser nulo")
    private String username;

    @NotNull(message = "Campo no puede ser nulo")
    private String email;

    @NotNull(message = "Campo no puede ser nulo")
    private String password;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

    @NotNull(message = "Campo no puede ser nulo")
    private ERole enumRol;
}
