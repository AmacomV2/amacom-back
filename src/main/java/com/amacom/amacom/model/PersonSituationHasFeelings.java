package com.amacom.amacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERSON_SITUATION_HAS_FEELINGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonSituationHasFeelings implements Serializable {

    private static final long serialVersionUID = -4402138802889003229L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_PERSON_SITUATION", nullable = false)
    private Long idPersonSituation;

    @Column(name = "ID_FEELINGS", nullable = false)
    private Long idFeelings;

    @Column(name = "PRIORITY")
    private Boolean priority;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_SITUATION",insertable = false,updatable = false)
    private PersonSituation personSituation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FEELINGS",insertable = false,updatable = false)
    private Feelings feelings;

}

