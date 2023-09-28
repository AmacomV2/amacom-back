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
    private Subject subjectParent;

    @ManyToOne
    @JoinColumn(name = "ID_RESULTADOS_ASOCIADOS", referencedColumnName = "ID")
    private Result resultadosAsociados;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "INDICACION_VALIDEZ")
    private String indicacionValidez;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

}

