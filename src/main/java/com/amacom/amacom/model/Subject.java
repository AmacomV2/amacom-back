package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUBJECT")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseModel {

    private static final long serialVersionUID = -7956521051265369329L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    private Subject parent;

    @ManyToOne
    @JoinColumn(name = "ASSOCIATED_RESULT_ID", referencedColumnName = "ID")
    private Result associatedResult;

    @Column(name = "NOMBRE", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "VALIDITY_INDICATOR")
    private String validityIndicator;

}
