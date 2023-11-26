package com.amacom.amacom.model.auth;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPersonRequest implements Serializable {
    private static final long serialVersionUID = -2432380621627365836L;

    private String name;

    private String lastName;

    private String documentNo;

    private String address;

    private String birthDate;

    private String occupation;

    private String imageUrl;

    private String privacyPolicy;

    private String consent;

    private String evaluationCompleted;

    private UUID personId;

    private UUID genderId;

    private UUID documentTypeId;

    private UUID civilStatusId;

    private UUID idRol;
}
