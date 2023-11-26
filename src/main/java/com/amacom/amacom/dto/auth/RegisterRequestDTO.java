package com.amacom.amacom.dto.auth;

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
public class RegisterRequestDTO implements Serializable {

    private static final long serialVersionUID = -2432380621627365836L;

    @NotNull(message = "Field cannot be null")
    private String username;

    @NotNull(message = "Field cannot be null")
    private String email;

    @NotNull(message = "Field cannot be null")
    private String password;

    @NotNull(message = "Field cannot be null")
    private String name;

    @NotNull(message = "Field cannot be null")
    private String lastName;

    @NotNull(message = "Field cannot be null")
    private String documentNo;

    private String address;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date birthDate;

    private String occupation;

    private String imageUrl;

    private String privacyPolicy;

    private String consentText;

    private String evaluationCompleted;

    private UUID personId;

    @NotNull(message = "Field cannot be null")
    private UUID genderId;

    @NotNull(message = "Field cannot be null")
    private UUID documentTypeId;

    private UUID civilStatusId;

    private UUID idRol;

}
