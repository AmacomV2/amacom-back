package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIAGNOSIS_HAS_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisHasSubject implements Serializable {

    private static final long serialVersionUID = 4981997479012943765L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID", referencedColumnName = "ID")
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;
}
