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
public class PersonAchievementDTO implements Serializable {

    private static final long serialVersionUID = -2851053834281343296L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private Integer score;

    @NotNull(message = "Field cannot be null")
    private UUID personId;

    private AchievementDTO achievement;

    @NotNull(message = "Field cannot be null")
    private UUID idAchievement;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

}
