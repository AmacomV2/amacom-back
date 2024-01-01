package com.amacom.amacom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.amacom.amacom.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EVENT")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseModel {

    private static final long serialVersionUID = -6253118475380318681L;

    @ManyToOne
    @JoinColumn(name = "EVENT_TYPE_ID", referencedColumnName = "ID", nullable = false)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
    private Person person;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ALL_DAY", columnDefinition = "TINYINT(1) DEFAULT '0'", nullable = false)
    private boolean allDay;

    @Column(name = "START", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "END", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @Column(name = "STATUS", columnDefinition = "('REGISTERED','COMPLETED') DEFAULT 'REGISTERED'", nullable = false)
    @Enumerated(EnumType.STRING)
    private EEventStatus eventStatus;

}
