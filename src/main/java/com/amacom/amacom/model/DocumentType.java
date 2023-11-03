package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TIPO_DOCUMENTO")
public class DocumentType implements Serializable {

    private static final long serialVersionUID = 3518906028901853324L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NOMBRE")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date updatedAt;
}
