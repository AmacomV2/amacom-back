package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DIAGNOSTIC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 2550690869610717045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_SITUACION_PERSONA", nullable = false)
    private Long idSituacionPersona;

    @Column(name = "RESULTADO_CONSULTA")
    private String resultadoConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "ALERTA_CONSULTA")
    private EAlertaConsulta alertaConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_CONSULTA")
    private EEstadoConsulta eEstadoConsulta;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SITUACION_PERSONA",insertable = false,updatable = false)
    private PersonSituation situacionPersona;
}

