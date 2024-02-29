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

    private DocumentType documentType;
    @NotNull(message = "Field cannot be null")
    private UUID documentTypeId;

    private Gender gender;
    @NotNull(message = "Field cannot be null")
    private UUID genderId;

    private CivilStatus civilStatus;
    private UUID civilStatusId;

    @NotNull(message = "Field cannot be null")
    private String documentNo;

    private String fullName;

    @NotNull(message = "Field cannot be null")
    private String lastName;

    @NotNull(message = "Field cannot be null")
    private String address;

    private String occupation;

    @NotNull(message = "Field cannot be null")
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date birthDate;

    @NotNull(message = "Field cannot be null")
    private Boolean consentText;

    @NotNull(message = "Field cannot be null")
    private Boolean privacyPolicy;

    @NotNull(message = "Field cannot be null")
    private Boolean evaluationCompleted;

    private String imageUrl;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date createdAt;

    @JsonFormat(pattern = ITools.PATTERN_DATE_TIME, timezone = ITools.TIME_ZONE_BOGOTA)
    private Date updatedAt;

}
