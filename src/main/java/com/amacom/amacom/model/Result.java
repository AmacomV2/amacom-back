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
@Table(name = "RESULT")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Result extends BaseModel {

    private static final long serialVersionUID = 1736935935852569721L;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID", referencedColumnName = "ID")
    private Diagnosis diagnosis;
}
