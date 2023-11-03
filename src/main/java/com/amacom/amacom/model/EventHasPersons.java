package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    private Event event;

}
