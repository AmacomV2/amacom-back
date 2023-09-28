package com.amacom.amacom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ALARM_SIGNS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSign implements Serializable {

    private static final long serialVersionUID = -6157600394839424100L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "TIPO_DESCRIPCION")
    private String tipoDescripcion;

    @Column(name = "LINK_IMAGEN")
    private String linkImagen;

    @Column(name = "ESTADO")
    private Boolean estado;

    @Column(name = "TIPO")
    private Boolean tipo;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

}

