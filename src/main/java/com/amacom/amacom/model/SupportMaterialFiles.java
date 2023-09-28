package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SUPPORT_MATERIAL_FILES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportMaterialFiles implements Serializable {

    private static final long serialVersionUID = -2972339910667614070L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_SUPPORT_MATERIAL")
    private Long idSupportMaterial;


    @Column(name = "PATH")
    private String path;

    @Column(name = "FECHA_HORA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    @Column(name = "FECHA_HORA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModificacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUPPORT_MATERIAL",insertable = false,updatable = false)
    private SupportMaterial supportMaterial;

}
