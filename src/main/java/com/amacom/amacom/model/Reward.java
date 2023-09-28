package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REWARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward implements Serializable {

    private static final long serialVersionUID = 6531493661054997902L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID")
    private Subject subject;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PUNTAJE_MINIMO")
    private Integer puntajeMinimo;

    @Column(name = "PUNTAJE_MAXIMO")
    private Integer puntajeMaximo;

    @Column(name = "NIVEL")
    private String nivel;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
}
