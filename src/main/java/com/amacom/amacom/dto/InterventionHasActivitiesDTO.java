package com.amacom.amacom.dto;

import com.amacom.amacom.model.Activity;
import com.amacom.amacom.model.Intervention;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InterventionHasActivitiesDTO implements Serializable {

    private static final long serialVersionUID = -2518264422653521181L;

    private UUID id;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idActivity;

    @NotNull(message = "Campo no puede ser nulo")
    private UUID idIntervention;


    private String estado;


    private String descripcion;

}
