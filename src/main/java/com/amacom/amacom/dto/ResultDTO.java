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
public class ResultDTO implements Serializable {

    private static final long serialVersionUID = 3211830820121705126L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID diagnosisId;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
