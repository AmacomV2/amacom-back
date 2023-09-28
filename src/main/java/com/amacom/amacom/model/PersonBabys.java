package com.amacom.amacom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSON_BABYS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonBabys implements Serializable {

    private static final long serialVersionUID = 259191779682240354L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID", nullable = false)
    private Persona padre;

    @ManyToOne
    @JoinColumn(name = "ID_BEBE", referencedColumnName = "ID", nullable = false)
    private Persona bebe;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

}