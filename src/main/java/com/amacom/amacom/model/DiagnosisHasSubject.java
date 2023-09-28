package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIAGNOSIS_HAS_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisHasSubject implements Serializable {

    private static final long serialVersionUID = 4981997479012943765L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_DIAGNOSIS", nullable = false)
    private Long idDiagnosis;

    @Column(name = "ID_SUBJECT", nullable = false)
    private Long idSubject;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DIAGNOSIS",insertable = false,updatable = false)
    private Diagnosis diagnosis;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUBJECT",insertable = false,updatable = false)
    private Subject subject;
}

