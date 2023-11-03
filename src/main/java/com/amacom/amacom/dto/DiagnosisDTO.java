package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosisDTO implements Serializable {

    private static final long serialVersionUID = -2273109586592405042L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID personSituationId;

    private String consultationResult;

    @NotNull(message = "Field cannot be null")
    @Enumerated(EnumType.STRING)
    private EConsultationAlert alertaConsulta;

    @NotNull(message = "Field cannot be null")
    @Enumerated(EnumType.STRING)
    private EConsultationStatus consultationStatus;

}
