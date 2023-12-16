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
@Table(name = "DOCUMENT_TYPE")
public class DocumentType extends BaseModel {

    private static final long serialVersionUID = 3518906028901853324L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ACRONYM", nullable = false)
    private String acronym;
}
