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
@Table(name = "INTERVENTION")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Intervention extends BaseModel {

    private static final long serialVersionUID = -3262685343739888502L;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID", referencedColumnName = "ID")
    private Diagnosis diagnosis;

}
