package com.amacom.amacom.dto;

import com.amacom.amacom.model.Result;
import com.amacom.amacom.model.Subject;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = -3192730388957913725L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idSubjectParent;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idResultadosAsociados;


    private String nombre;


    private String indicacionValidez;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;


}
