package com.amacom.amacom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.amacom.amacom.model.CivilStatus;
import com.amacom.amacom.model.DocumentType;
import com.amacom.amacom.model.Gender;
import com.amacom.amacom.util.ITools;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = -7256480951049205013L;

    private UUID id;

    private String name;

    @NotNull(message = "Field cannot be null")
    private DocumentType documentType;
    private UUID documentTypeId;

    @NotNull(message = "Field cannot be null")
    private Gender gender;
    private UUID genderId;

    @NotNull(message = "Field cannot be null")
    private CivilStatus civilStatus;
    private UUID civilStatusId;

    @NotNull(message = "Field cannot be null")
    private String documentNo;

    @NotNull(message = "Field cannot be null")
    private String fullName;

    @NotNull(message = "Field cannot be null")
    private String lastName;

    @NotNull(message = "Field cannot be null")
    private String address;

    private String occupation;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date birthDay;

    @NotNull(message = "Field cannot be null")
    private Boolean consentText;

    @NotNull(message = "Field cannot be null")
    private Boolean privacyPolicy;

    @NotNull(message = "Field cannot be null")
    private Boolean evaluation_completed;

    private String imageUrl;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_FECHA_HORA_MINUTO, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date updatedAt;

}
