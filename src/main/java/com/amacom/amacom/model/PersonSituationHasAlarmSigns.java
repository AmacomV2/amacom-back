package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSON_SITUATION_HAS_ALARM_SIGNS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituationHasAlarmSigns implements Serializable {

    private static final long serialVersionUID = 3203506404323347807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_PERSON_SITUATION", nullable = false)
    private Long idPersonSituation;

    @Column(name = "ID_ALARM_SIGNS", nullable = false)
    private Long idAlarmSigns;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_SITUATION",insertable = false,updatable = false)
    private PersonSituation personSituation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ALARM_SIGNS",insertable = false,updatable = false)
    private AlarmSign alarmSign;
}
