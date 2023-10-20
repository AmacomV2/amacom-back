package com.amacom.amacom.dto;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = -4751522369108865599L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;


    @NotNull(message = "Campo no puede ser nulo")
    private UUID idRol;


    @NotNull(message = "Campo no puede ser nulo")
    private String username;

    @NotNull(message = "Campo no puede ser nulo")
    private String email;

    private String password;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;


    private ERole enumRol;



}
