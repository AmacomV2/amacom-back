package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PERSONA")
public class Persona implements Serializable {

    private static final long serialVersionUID = 3680251739268601659L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID")
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "ID_GENERO", referencedColumnName = "ID")
    private Genero genero;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;


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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date fechaHoraCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date fechaHoraModificacion;


    @Formula("CONCAT(NOMBRE, ' ', APELLIDO)")
    private String nombreAndApellido;
}
