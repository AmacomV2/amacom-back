package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUPPORT_MATERIAL_FILES")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SupportMaterialFiles extends BaseModel {

    private static final long serialVersionUID = -2972339910667614070L;

    @ManyToOne
    @JoinColumn(name = "SUPPORT_MATERIAL_ID", referencedColumnName = "ID")
    private SupportMaterial supportMaterial;

    @Column(name = "PATH")
    private String path;

}
