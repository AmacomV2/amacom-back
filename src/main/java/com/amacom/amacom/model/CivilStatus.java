package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "CIVIL_STATUS")
public class CivilStatus extends BaseModel {

    private static final long serialVersionUID = -5401910043243180613L;

    @Column(name = "NAME")
    private String name;

}
