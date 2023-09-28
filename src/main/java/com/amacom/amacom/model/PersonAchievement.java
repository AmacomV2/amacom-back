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
@Table(name = "PERSON_ACHIEVEMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAchievement implements Serializable {

    private static final long serialVersionUID = -6424297099692066046L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PUNTAJE")
    private EPuntaje puntaje;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_ACHIEVEMENT", referencedColumnName = "ID")
    private Achievement achievement;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

}

