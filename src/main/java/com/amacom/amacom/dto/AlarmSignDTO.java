package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.EAlarmSignType;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlarmSignDTO implements Serializable {

    private static final long serialVersionUID = -7236898171713756877L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private String name;

    private String description;

    private String imageUrl;

    private Boolean status;

    @NotNull(message = "Field cannot be null")
    private EAlarmSignType type;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

}
