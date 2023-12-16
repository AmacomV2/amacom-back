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
@Table(name = "GENDER")
public class Gender extends BaseModel {

    private static final long serialVersionUID = 6056692568582650701L;

    @Column(name = "NAME")
    private String name;
}
