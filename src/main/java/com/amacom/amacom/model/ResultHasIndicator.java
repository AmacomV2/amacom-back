package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RESULT_HAS_INDICATOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultHasIndicator implements Serializable {

    private static final long serialVersionUID = -7094155733232886844L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_RESULT", nullable = false)
    private Long idResult;

    @Column(name = "ID_INDICATOR", nullable = false)
    private Long idIndicator;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESULT",insertable = false,updatable = false)
    private Result result;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INDICATOR",insertable = false,updatable = false)
    private Indicator indicator;
}

