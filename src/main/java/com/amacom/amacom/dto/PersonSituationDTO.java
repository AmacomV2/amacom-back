package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class PersonSituationDTO implements Serializable {

    private static final long serialVersionUID = -6009951415841197929L;

    private UUID id;

    private UUID personId;

    private UUID createdById;

    @NotNull(message = "Field cannot be null")
    private UUID subjectId;

    @NotNull(message = "Field cannot be null")
    private List<UUID> feelings;

    @NotNull(message = "Field cannot be null")
    private List<UUID> babyAlarmSigns;

    @NotNull(message = "Field cannot be null")
    private List<UUID> motherAlarmSigns;

    private DiagnosisDTO currentDiagnosis;

    @NotNull(message = "Field cannot be null")
    private String description;

    @NotNull(message = "Field cannot be null")
    private String firstThought;

    @NotNull(message = "Field cannot be null")
    private String behavior;

    @NotNull(message = "Field cannot be null")
    private Integer affectationDegree;

    private String nursingAssessment;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
