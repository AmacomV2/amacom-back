package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ALARM_SIGNS")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSign extends BaseModel {

    private static final long serialVersionUID = -6157600394839424100L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION_TYPE")
    private String descriptionType;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "TYPE")
    private Boolean type;

}
