package com.amacom.amacom.dto;

import com.amacom.amacom.model.EAlertaConsulta;
import com.amacom.amacom.model.EEstadoConsulta;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosisDTO implements Serializable {

    private static final long serialVersionUID = -2273109586592405042L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idPersonSituation;


    private String resultadoConsulta;

    @NotNull(message = "Campo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private EAlertaConsulta alertaConsulta;

    @NotNull(message = "Campo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private EEstadoConsulta eEstadoConsulta;

}
