package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIAGNOSIS")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis extends BaseModel {

    private static final long serialVersionUID = 2550690869610717045L;

    @ManyToOne
    @JoinColumn(name = "PERSON_SITUATION_ID", referencedColumnName = "ID")
    private PersonSituation personSituation;

    @Column(name = "CONSULTATION_RESULT")
    private String consultationResult;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSULTATION_ALERT")
    private EConsultationAlert consultationAlert;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSULTATION_STATUS")
    private EConsultationStatus consultationStatus;
}
