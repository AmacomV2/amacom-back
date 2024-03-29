package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARAMS")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Params extends BaseModel {

    private static final long serialVersionUID = -1760088231036927881L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TERMS_AND_CONDITIONS", nullable = false, columnDefinition = "TEXT")
    private String termsAndConditions;

    @Column(name = "MAIL_HOST", nullable = false)
    private String mailHost;

    @Column(name = "MAIL_PORT", nullable = false)
    private Integer mailPort;

    @Column(name = "MAIL_USERNAME", nullable = false)
    private String mailUsername;

    @Column(name = "MAIL_PASSWORD", nullable = false)
    private String mailPassword;

}
