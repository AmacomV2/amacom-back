package com.amacom.amacom.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDTO implements Serializable {

    private static final long serialVersionUID = -2432380621627365836L;


    @NotNull(message = "Campo no puede ser nulo")
    private String username;

    @NotNull(message = "Campo no puede ser nulo")
    private String email;

    @NotNull(message = "Campo no puede ser nulo")
    private String password;

    private Long idPersona;

    private Long idRol;

}
