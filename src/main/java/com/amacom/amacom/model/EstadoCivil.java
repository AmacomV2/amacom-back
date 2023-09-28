package com.amacom.amacom.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ESTADO_CIVIL")
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = -5401910043243180613L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date fechaHoraCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date fechaHoraModificacion;

}
