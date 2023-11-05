package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERSONA")
public class Person implements Serializable {

    private static final long serialVersionUID = 3680251739268601659L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID")
    private CivilStatus civilStatus;

    @ManyToOne
    @JoinColumn(name = "ID_GENERO", referencedColumnName = "ID")
    private Gender gender;

    @Column(name = "DOCUMENTO", nullable = false)
    private String documentNo;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "APELLIDO", nullable = false)
    private String lastName;

    @Column(name = "DIRECCION", nullable = false)
    private String address;

    @Column(name = "OCUPACION")
    private String occupation;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Column(name = "CONSENTIMIENTO", nullable = false)
    private Boolean consentText;

    @Column(name = "POLITICA_PRIVACIDAD", nullable = false)
    private Boolean privacyPolicy;

    @Column(name = "EVALUACION_COMPLETADA", nullable = false)
    private Boolean evaluation_completed;

    @Column(name = "ID_FOTO_USUARIO")
    private String imageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_MODIFICACION")
    private Date updatedAt;

    @Formula("CONCAT(NOMBRE, ' ', APELLIDO)")
    private String fullName;
}
