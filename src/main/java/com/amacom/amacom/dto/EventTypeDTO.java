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
public class EventTypeDTO implements Serializable {

    private static final long serialVersionUID = 7914923759382170663L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Field cannot be null")
    private String color;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME_SECOND, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME_SECOND, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
