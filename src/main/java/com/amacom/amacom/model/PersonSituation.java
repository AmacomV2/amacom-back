package com.amacom.amacom.model;

import javax.persistence.*;

import com.amacom.amacom.model.auth.User;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PERSON_SITUATION")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituation extends BaseModel {

    private static final long serialVersionUID = 5551518673194641952L;

    @Id
    @Column(name="ID")
    private UUID id;

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

    @OneToMany(mappedBy = "personSituation", cascade = CascadeType.ALL, orphanRemoval = true)
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
