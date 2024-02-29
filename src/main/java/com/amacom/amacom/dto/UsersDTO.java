package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = -4751522369108865599L;

    private UUID id;

    @NotNull(message = "Field cannot be null")
    private UUID personId;

    @NotNull(message = "Field cannot be null")
    private UUID idRol;

    @NotNull(message = "Field cannot be null")
    private String username;

    @NotNull(message = "Field cannot be null")
    private String email;

    private String password;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

    private ERole enumRol;

}
