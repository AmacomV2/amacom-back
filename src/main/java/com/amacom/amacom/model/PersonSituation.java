package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.amacom.amacom.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERSON_SITUATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituation implements Serializable {

    private static final long serialVersionUID = 5551518673194641952L;

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;

    @Column(name = "FIRST_THOUGHT", nullable = false)
    private String description;

    @Column(name = "PRIMER_PENSAMIENTO", nullable = false)
    private String firstThought;

    @Column(name = "BEHAVIOR", nullable = false)
    private String behavior;

    @Enumerated(EnumType.STRING)
    @Column(name = "AFFECTATION_DEGREE", nullable = false)
    private EAffectationDegree affectationDegree;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "NURSING_ASSESSMENT")
    private String nursingAssessment;
}
