package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.EAffectationDegree;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonSituationDTO implements Serializable {

    private static final long serialVersionUID = -6009951415841197929L;

    private UUID id;

    private UUID personId;

    private UUID userId;

    @NotNull(message = "Field cannot be null")
    private UUID subjectId;

    @NotNull(message = "Field cannot be null")
    private UUID situationTypeId;

    private String description;

    private String firstThought;

    private String behavior;

    @NotNull(message = "Field cannot be null")
    @Enumerated(EnumType.STRING)
    private EAffectationDegree affectationDegree;

    private String nursingAssessment;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
