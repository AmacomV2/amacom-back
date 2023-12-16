package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIAGNOSIS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 2550690869610717045L;

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_SITUACION_PERSONA", referencedColumnName = "ID")
    private PersonSituation personSituation;

    @Column(name = "RESULTADO_CONSULTA")
    private String consultationResult;

    @Enumerated(EnumType.STRING)
    @Column(name = "ALERTA_CONSULTA")
    private EConsultationAlert consultationAlert;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_CONSULTA")
    private EConsultationStatus consultationStatus;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
}
