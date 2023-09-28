package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTITUTION_SERVICE_PERSON")
public class InstitutionServicePerson implements Serializable {

    private static final long serialVersionUID = 993579400240505221L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;

    @Column(name = "ID_INSTITUCION_SERVICIO")
    private Long idInstitucionServicio;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSTITUCION_SERVICIO",insertable = false,updatable = false)
    private InstitutionService institucionServicio;
}
