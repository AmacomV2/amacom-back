package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "EVENT_HAS_PERSONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHasPersons implements Serializable {

    private static final long serialVersionUID = 1246642940174979130L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    private Event event;

}
