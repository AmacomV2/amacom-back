package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "INTERVENTION_HAS_ACTIVITIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionHasActivities implements Serializable {

    private static final long serialVersionUID = -5688539495634138818L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVITY", referencedColumnName = "ID")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "ID_INTERVENTION", referencedColumnName = "ID")
    private Intervention intervention;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
