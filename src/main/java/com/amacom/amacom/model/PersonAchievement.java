package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "PERSON_ACHIEVEMENT")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PersonAchievement extends BaseModel {

    private static final long serialVersionUID = -6424297099692066046L;

    @Column(name = "SCORE", nullable = false, columnDefinition = "INT DEFAULT '0'")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ACHIEVEMENT_ID", referencedColumnName = "ID")
    private Achievement achievement;

}
