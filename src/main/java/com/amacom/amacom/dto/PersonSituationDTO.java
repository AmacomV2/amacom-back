package com.amacom.amacom.dto;

import com.amacom.amacom.model.EGradoAfectacion;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.model.TipoSituacion;
import com.amacom.amacom.model.auth.Usuario;
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
public class PersonSituationDTO implements Serializable {

    private static final long serialVersionUID = -6009951415841197929L;

    private UUID id;
    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idUsuario;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idSubject;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idTipoSituacion;


    private String descripcion;


    private String primerPensamiento;


    private String comportamiento;

    @NotNull(message = "Campo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private EGradoAfectacion gradoAfectacion;


    private String evaluacionEnfermeria;


    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

}
