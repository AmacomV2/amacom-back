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

    private UUID personId;

    @NotNull(message = "Field cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Field cannot be null")
    private boolean allDay = false;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date start;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date end;

    @Enumerated(EnumType.STRING)
    private EEventStatus eventStatus = EEventStatus.REGISTERED;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME_SECOND, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME_SECOND, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
