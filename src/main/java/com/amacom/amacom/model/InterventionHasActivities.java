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
@Table(name = "INTERVENTION_HAS_ACTIVITIES")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InterventionHasActivities extends BaseModel {

    private static final long serialVersionUID = -5688539495634138818L;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVITY", referencedColumnName = "ID")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "ID_INTERVENTION", referencedColumnName = "ID")
    private Intervention intervention;

    @Column(name = "STATUS", columnDefinition = "TINYINT(1) DEFAULT '1'", nullable = false)
    private boolean status;

    @Column(name = "DESCRIPTION")
    private String description;

    public boolean getStatus() {
        return status;
    }

}
