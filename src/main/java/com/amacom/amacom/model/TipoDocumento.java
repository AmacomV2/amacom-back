package com.amacom.amacom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 3518906028901853324L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CODIGO")
    private String codigo;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date fechaHoraCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date fechaHoraModificacion;
}
