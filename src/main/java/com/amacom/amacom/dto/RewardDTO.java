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
public class RewardDTO implements Serializable {

    private static final long serialVersionUID = -944652145402456588L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Field cannot be null")
    private String image;

    @NotNull(message = "Field cannot be null")
    private Integer minScore;

    @NotNull(message = "Field cannot be null")
    private Integer maxScore;

    private String level;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

}
