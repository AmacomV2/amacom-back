package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "EVENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    private static final long serialVersionUID = -6253118475380318681L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID")
    private TipoEvento tipoEvento;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CREA", referencedColumnName = "ID")
    private Usuario usuario;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "COMIENZO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date comienzo;

    @Column(name = "FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;

    @Column(name = "ESTADO_EVENTO")
    @Enumerated(EnumType.STRING)
    private EEstadoEvento estadoEvento;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;
}
