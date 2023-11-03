package com.amacom.amacom.model.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;

    @Column(name = "DESCRIPCION")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole enumRol;
}
