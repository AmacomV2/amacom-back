package com.amacom.amacom.model;

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
@Table(name = "INSTITUTION_SERVICE_PERSON")
public class InstitutionServicePerson extends BaseModel {

    private static final long serialVersionUID = 993579400240505221L;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "INSTITUTION_SERVICE_ID", referencedColumnName = "ID")
    private InstitutionService institutionService;
}
