package com.amacom.amacom.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenderDTO implements Serializable {

    private static final long serialVersionUID = -819749689979675627L;

    private UUID id;

    private String name;

    @JsonFormat(timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Instant createdAt;

    @JsonFormat(timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Instant updatedAt;
}
