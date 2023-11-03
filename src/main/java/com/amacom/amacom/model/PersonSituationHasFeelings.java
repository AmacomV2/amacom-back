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
@Table(name = "PERSON_SITUATION_HAS_FEELINGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituationHasFeelings implements Serializable {

    private static final long serialVersionUID = -4402138802889003229L;

    @Id
    @Column(name = "ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON_SITUATION", referencedColumnName = "ID")
    private PersonSituation personSituation;

    @ManyToOne
    @JoinColumn(name = "ID_FEELINGS", referencedColumnName = "ID")
    private Feelings feelings;

    @Column(name = "PRIORITY")
    private Boolean priority;

}
