package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosisHasSubjectDTO implements Serializable {

    private static final long serialVersionUID = 1935596828457240853L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID diagnosisId;

    @NotNull(message = "Field cannot be null")
    private UUID subjectId;

    private String subjectName;

}
