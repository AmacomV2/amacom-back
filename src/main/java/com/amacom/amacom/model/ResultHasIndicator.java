package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RESULT_HAS_INDICATOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultHasIndicator implements Serializable {

    private static final long serialVersionUID = -7094155733232886844L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_RESULT", referencedColumnName = "ID")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "ID_INDICATOR", referencedColumnName = "ID")
    private Indicator indicator;

}
