package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSON_SITUATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituation implements Serializable {

    private static final long serialVersionUID = 5551518673194641952L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;

    @Column(name = "ID_USUARIO_CREA", nullable = false)
    private Long idUsuarioCrea;

    @Column(name = "ID_SUBJECT", nullable = false)
    private Long idSubject;

    @Column(name = "ID_SITUATION_TYPE", nullable = false)
    private Long idTipoSituacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRIMER_PENSAMIENTO")
    private String primerPensamiento;

    @Column(name = "COMPORTAMIENTO")
    private String comportamiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "GRADO_AFECTACION")
    private EGradoAfectacion gradoAfectacion;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

    @Column(name = "EVALUACION_ENFERMERIA")
    private String evaluacionEnfermeria;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_CREA",insertable = false,updatable = false)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUBJECT",insertable = false,updatable = false)
    private Subject subject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SITUATION_TYPE",insertable = false,updatable = false)
    private TipoSituacion tipoSituacion;
}
