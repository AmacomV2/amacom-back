package com.amacom.amacom.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EVENT_HAS_PERSONS")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EventHasPersons extends BaseModel {

    private static final long serialVersionUID = 1246642940174979130L;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")
    private Event event;

}
