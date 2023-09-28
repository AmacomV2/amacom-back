package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CREA", referencedColumnName = "ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "ID_SITUATION_TYPE", referencedColumnName = "ID")
    private TipoSituacion tipoSituacion;

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
}
