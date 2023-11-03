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
@Table(name = "PERSON_SITUATION_HAS_ALARM_SIGNS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituationHasAlarmSigns implements Serializable {

    private static final long serialVersionUID = 3203506404323347807L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON_SITUATION", referencedColumnName = "ID")
    private PersonSituation personSituation;

    @ManyToOne
    @JoinColumn(name = "ID_ALARM_SIGNS", referencedColumnName = "ID")
    private AlarmSign alarmSign;

}
