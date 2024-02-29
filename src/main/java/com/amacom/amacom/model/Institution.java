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
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTITUTION")
public class Institution extends BaseModel {

    private static final long serialVersionUID = 1491571920947412552L;

    @ManyToOne
    @JoinColumn(name = "INSTITUTION_TYPE_ID", referencedColumnName = "ID")
    private InstitutionType institutionType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

}
