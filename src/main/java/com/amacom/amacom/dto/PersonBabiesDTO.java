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
public class PersonBabiesDTO implements Serializable {

    private static final long serialVersionUID = 1806980354878713001L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID idParent;

    @NotNull(message = "Field cannot be null")
    private UUID idChild;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
