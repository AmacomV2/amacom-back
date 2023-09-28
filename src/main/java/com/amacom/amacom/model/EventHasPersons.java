package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EVENT_HAS_PERSONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHasPersons implements Serializable {

    private static final long serialVersionUID = 1246642940174979130L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;


    @Column(name = "ID_EVENTO", nullable = false)
    private Long idEvento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EVENTO",insertable = false,updatable = false)
    private Event evento;

}
