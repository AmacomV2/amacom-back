package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTITUTION_SERVICE_PERSON")
public class InstitutionServicePerson implements Serializable {

    private static final long serialVersionUID = 993579400240505221L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "ID_INSTITUCION_SERVICIO", referencedColumnName = "ID")
    private InstitutionService institutionService;
}
