package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "SUPPORT_MATERIAL_HAS_SUBJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportMaterialHasSubject implements Serializable {

    private static final long serialVersionUID = -8621283640601114675L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_SUPPORT_MATERIAL", referencedColumnName = "ID")
    private SupportMaterial supportMaterial;

    @ManyToOne
    @JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID")
    private Subject subject;

}
