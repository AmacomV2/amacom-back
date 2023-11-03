package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.EEventStatus;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO implements Serializable {

    private static final long serialVersionUID = 3445418192544864166L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID eventTypeId;
    private String eventTypeName;

    private UUID createdBy;

    private String title;

    private String description;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date start;

    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fin;

    @Enumerated(EnumType.STRING)
    private EEventStatus eventStatus;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
