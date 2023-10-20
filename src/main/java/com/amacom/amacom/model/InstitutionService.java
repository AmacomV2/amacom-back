package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
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
@Table(name = "INSTITUTION_SERVICE")
public class InstitutionService implements Serializable {

    private static final long serialVersionUID = 5187833655453018675L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CREA", referencedColumnName = "ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID")
    private Services services;

    @ManyToOne
    @JoinColumn(name = "ID_INSTITUCION", referencedColumnName = "ID")
    private Institution institution;

}
