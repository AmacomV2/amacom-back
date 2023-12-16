package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUPPORT_MATERIAL_HAS_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportMaterialHasSubject implements Serializable {

    private static final long serialVersionUID = -8621283640601114675L;

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "SUPPORT_MATERIAL_ID", referencedColumnName = "ID")
    private SupportMaterial supportMaterial;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;

}
