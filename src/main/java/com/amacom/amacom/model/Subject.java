package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements Serializable {

    private static final long serialVersionUID = -7956521051265369329L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT_PARENT", referencedColumnName = "ID")
    private Subject parent;

    @ManyToOne
    @JoinColumn(name = "ID_RESULTADOS_ASOCIADOS", referencedColumnName = "ID")
    private Result associatedResults;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "INDICACION_VALIDEZ")
    private String validityIndicator;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
