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
@Table(name = "REWARD")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Reward extends BaseModel {

    private static final long serialVersionUID = 6531493661054997902L;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    private Subject subject;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MIN_SCORE")
    private Integer minScore;

    @Column(name = "MAX_SCORE")
    private Integer maxScore;

    @Column(name = "LEVEL")
    private String level;

}
