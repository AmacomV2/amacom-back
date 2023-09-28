package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SUPPORT_MATERIAL_HAS_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportMaterialHasSubject implements Serializable {

    private static final long serialVersionUID = -8621283640601114675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_SUPPORT_MATERIAL", nullable = false)
    private Long idSupportMaterial;

    @Column(name = "ID_SUBJECT", nullable = false)
    private Long idSubject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUPPORT_MATERIAL",insertable = false,updatable = false)
    private SupportMaterial supportMaterial;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUBJECT",insertable = false,updatable = false)
    private Subject subject;

}
