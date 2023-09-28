package com.amacom.amacom.model.auth;

import com.amacom.amacom.model.auth.ERole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ROLES")
public class Rol implements Serializable {

    private static final long serialVersionUID = 4448364954833682456L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date fechaHoraCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date fechaHoraModificacion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole enumRol;
}
