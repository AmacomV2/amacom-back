package com.amacom.amacom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SITUATION_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituationType implements Serializable {

    private static final long serialVersionUID = 1810044760869215616L;

    @Id
    @Column(name = "ID", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

}
