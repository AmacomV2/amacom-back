package com.amacom.amacom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERSON")
public class Person extends BaseModel {

    private static final long serialVersionUID = 3680251739268601659L;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID", referencedColumnName = "ID")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "CIVIL_STATUS_ID", referencedColumnName = "ID")
    private CivilStatus civilStatus;

    @ManyToOne
    @JoinColumn(name = "GENDER_ID", referencedColumnName = "ID")
    private Gender gender;

    @Column(name = "DOCUMENT_NO", nullable = false)
    private String documentNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "OCCUPATION")
    private String occupation;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "CONSENT_TEXT", nullable = false)
    private Boolean consentText;

    @Column(name = "PRIVACY_POLICY", nullable = false)
    private Boolean privacyPolicy;

    @Column(name = "EVALUATION_COMPLETED", nullable = false)
    private Boolean evaluationCompleted;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Formula("CONCAT(NAME, ' ', LAST_NAME)")
    private String fullName;
}
