package com.amacom.amacom.dto;

import com.amacom.amacom.model.EEstadoEvento;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO implements Serializable {

    private static final long serialVersionUID = 3445418192544864166L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idTipoEvento;
    private String nombreTipoEvento;

    private Long idUsuarioCrea;

    private String titulo;

    private String descripcion;

    @NotNull(message = "Campo no puede ser nulo")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date comienzo;


    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fin;

    @Enumerated(EnumType.STRING)
    private EEstadoEvento estadoEvento;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

}
