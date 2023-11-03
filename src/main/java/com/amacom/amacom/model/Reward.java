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
    private String name;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "PUNTAJE_MINIMO")
    private Integer minScore;

    @Column(name = "PUNTAJE_MAXIMO")
    private Integer maxScore;

    @Column(name = "NIVEL")
    private String level;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
