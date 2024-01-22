package com.amacom.amacom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.amacom.amacom.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERSON_SITUATION")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituation extends BaseModel {

    private static final long serialVersionUID = 5551518673194641952L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIAGNOSIS_ID", referencedColumnName = "ID", nullable = true)
    private Diagnosis currentDiagnosis;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personSituation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonSituationHasFeelings> feelings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personSituation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonSituationHasAlarmSigns> alarmSigns;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "FIRST_THOUGHT", nullable = false)
    private String firstThought;

    @Column(name = "BEHAVIOR", nullable = false)
    private String behavior;

    @Column(name = "AFFECTATION_DEGREE", nullable = false, columnDefinition = "INT(1)")
    private Integer affectationDegree;

    @Column(name = "NURSING_ASSESSMENT")
    private String nursingAssessment;
}
