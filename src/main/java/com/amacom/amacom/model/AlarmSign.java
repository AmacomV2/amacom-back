package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IMAGE_URL", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "STATUS")
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", columnDefinition = "ENUM('BABY','MOTHER')")
    private EAlarmSignType type;

}
