package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = -3192730388957913725L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID parentId;

    @NotNull(message = "Field cannot be null")
    private UUID idResultadosAsociados;

    private String name;

    private String indicacionValidez;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
