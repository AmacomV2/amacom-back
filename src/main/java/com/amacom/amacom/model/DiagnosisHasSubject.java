package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

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
    @JoinColumn(name = "ID_DIAGNOSIS", referencedColumnName = "ID")
    private Diagnosis diagnosis;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID")
    private Subject subject;
}

