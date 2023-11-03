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
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CREA", referencedColumnName = "ID")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "ID_SITUATION_TYPE", referencedColumnName = "ID")
    private SituationType situationType;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "PRIMER_PENSAMIENTO")
    private String firstThought;

    @Column(name = "COMPORTAMIENTO")
    private String behavior;

    @Enumerated(EnumType.STRING)
    @Column(name = "GRADO_AFECTACION")
    private EAffectationDegree affectationDegree;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "EVALUACION_ENFERMERIA")
    private String nursingAssessment;
}
