package com.amacom.amacom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTITUTION_SERVICE")
public class InstitutionService extends BaseModel {

    private static final long serialVersionUID = 5187833655453018675L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EFFECTIVENESS_START", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date effectivenessStart;

    @Column(name = "EFFECTIVENESS_END", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date effectivenessEND;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
    private Services services;

    @ManyToOne
    @JoinColumn(name = "INSTITUTION_ID", referencedColumnName = "ID")
    private Institution institution;

}
