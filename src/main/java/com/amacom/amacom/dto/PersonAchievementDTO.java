package com.amacom.amacom.dto;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.EPuntaje;
import com.amacom.amacom.model.Persona;
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
public class PersonAchievementDTO implements Serializable {

    private static final long serialVersionUID = -2851053834281343296L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private EPuntaje puntaje;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersona;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idAchievement;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraCreacion;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHoraModificacion;

}
