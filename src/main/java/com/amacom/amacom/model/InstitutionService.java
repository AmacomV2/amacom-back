package com.amacom.amacom.model;

import com.amacom.amacom.model.auth.Usuario;
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
@Table(name = "INSTITUTION_SERVICE")
public class InstitutionService implements Serializable {

    private static final long serialVersionUID = 5187833655453018675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_USUARIO_CREA")
    private Long idUsuarioCrea;

    @Column(name = "ID_SERVICIO")
    private Long idServicio;

    @Column(name = "ID_INSTITUCION")
    private Long idInstitucion;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_CREA",insertable = false,updatable = false)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICIO",insertable = false,updatable = false)
    private Services servicio;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSTITUCION",insertable = false,updatable = false)
    private Institution institucion;


}
