package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EVENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    private static final long serialVersionUID = -6253118475380318681L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_TIPO_EVENTO")
    private Long idTipoEvento;

    @Column(name = "ID_USUARIO_CREA")
    private Long idUsuarioCrea;

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


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_EVENTO",insertable = false,updatable = false)
    private TipoEvento tipoEvento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_CREA",insertable = false,updatable = false)
    private Usuario usuario;
}
