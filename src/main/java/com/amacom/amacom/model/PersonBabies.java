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
@Table(name = "PERSON_BABIES")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PersonBabies extends BaseModel {

    private static final long serialVersionUID = 259191779682240354L;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = false)
    private Person parent;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
    private Person child;

}
