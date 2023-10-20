package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "DIAGNOSTIC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 2550690869610717045L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_SITUACION_PERSONA", referencedColumnName = "ID")
    private PersonSituation personSituation;

    @Column(name = "RESULTADO_CONSULTA")
    private String resultadoConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "ALERTA_CONSULTA")
    private EAlertaConsulta eAlertaConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_CONSULTA")
    private EEstadoConsulta eEstadoConsulta;
}

