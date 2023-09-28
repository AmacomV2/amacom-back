package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "INTERVENTION_HAS_ACTIVITIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionHasActivities implements Serializable {

    private static final long serialVersionUID = -5688539495634138818L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_ACTIVITY", nullable = false)
    private Long idActivity;

    @Column(name = "ID_INTERVENTION", nullable = false)
    private Long idIntervention;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "DESCRIPCION")
    private String descripcion;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTIVITY",insertable = false,updatable = false)
    private Activity activity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INTERVENTION",insertable = false,updatable = false)
    private Intervention intervention;

}
