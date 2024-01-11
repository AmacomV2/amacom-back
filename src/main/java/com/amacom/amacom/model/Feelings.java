package com.amacom.amacom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table(name = "FEELINGS")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Feelings extends BaseModel {

    private static final long serialVersionUID = -5862576302502380715L;

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "STATUS")
    private String status;

}
