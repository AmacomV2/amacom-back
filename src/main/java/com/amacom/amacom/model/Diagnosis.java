package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amacom.amacom.model.auth.User;

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
    @JoinColumn(name = "PERSON_SITUATION_ID", referencedColumnName = "ID", nullable = false)
    private PersonSituation personSituation;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = false)
    private User createdBy;

    @Column(name = "CONSULTATION_RESULT", columnDefinition = "TEXT")
    private String consultationResult;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSULTATION_ALERT", columnDefinition = "ENUM('INFORMATION','TO_REVIEW','URGENT')", nullable = false)
    private EConsultationAlert consultationAlert;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSULTATION_STATUS", columnDefinition = "ENUM('PENDING','IN_PROGRESS','COMPLETED')", nullable = false)
    private EConsultationStatus consultationStatus;

}
