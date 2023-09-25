package com.amacom.amacom.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PASSWORD_TOKEN")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String codigo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;


}
