package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PERSONA")
public class Persona implements Serializable {

    private static final long serialVersionUID = 3680251739268601659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_TIPO_DOCUMENTO", nullable = false)
    private Long idTipoDocumento;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;

    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;

    @Column(name = "OCUPACION")
    private String ocupacion;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "CONSENTIMIENTO", nullable = false)
    private Boolean consentimiento;

    @Column(name = "POLITICA_PRIVACIDAD", nullable = false)
    private Boolean politicaPrivacidad;

    @Column(name = "EVALUACION_COMPLETADA", nullable = false)
    private Boolean evaluacionCompletada;

    @Column(name = "ID_FOTO_USUARIO")
    private String linkFoto;

    /*@Column(name = "ID_FOTO_USUARIO")
    @Getter
    private Long idFotoUsuario; */


    //Relaciones

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_DOCUMENTO",insertable = false,updatable = false)
    private TipoDocumento tipoDocumento;

}
