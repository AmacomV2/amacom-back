package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IMAGE", columnDefinition = "TEXT", nullable = false)
    private String image;

    @Column(name = "MIN_SCORE", nullable = false, columnDefinition = "INT")
    private Integer minScore;

    @Column(name = "MAX_SCORE", nullable = false, columnDefinition = "INT")
    private Integer maxScore;

    @Column(name = "LEVEL")
    private String level;

}
