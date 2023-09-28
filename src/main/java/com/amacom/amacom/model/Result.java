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
@Table(name = "RESULT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = 1736935935852569721L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_DIAGNOSIS", referencedColumnName = "ID")
    private Diagnosis diagnosis;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
}
