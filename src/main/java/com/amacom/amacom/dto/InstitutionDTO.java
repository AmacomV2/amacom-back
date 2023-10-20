package com.amacom.amacom.dto;

import com.amacom.amacom.model.TipoInstitucion;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InstitutionDTO implements Serializable {

    private static final long serialVersionUID = -7463433795050078267L;

    private UUID id;


    @NotNull(message = "Campo no puede ser nulo")
    private UUID idTipoInstitucion;


    private String nombre;


    private String descripcion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

}
