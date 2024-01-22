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
public class InterventionHasActivitiesDTO implements Serializable {

    private static final long serialVersionUID = -2518264422653521181L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID idActivity;

    private String activityName;

    private String activityDescription;

    @NotNull(message = "Field cannot be null")
    private UUID idIntervention;

    private boolean status;

    private String description;

}
