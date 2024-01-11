package com.amacom.amacom.model;

import javax.persistence.*;

import com.amacom.amacom.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PERSON_SITUATION")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituation extends BaseModel {

    private static final long serialVersionUID = 5551518673194641952L;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID", referencedColumnName = "ID", nullable = true)
    private Diagnosis currentDiagnosis;

    //@ManyToOne
    @OneToMany(mappedBy = "personSituation")
    private List<PersonSituationHasFeelings> feelings;

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
