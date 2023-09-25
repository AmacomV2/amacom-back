package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LOGBOOK")
public class LogBook implements Serializable {

    private static final long serialVersionUID = 7552610927189085219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_PERSONA", nullable = false)
    private UUID idPersona;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;



    // Relaciones

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA",insertable = false,updatable = false)
    private Persona persona;

}

