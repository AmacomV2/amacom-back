package com.amacom.amacom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "SERVICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services implements Serializable {

    private static final long serialVersionUID = 7336106518082207521L;
    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

}
