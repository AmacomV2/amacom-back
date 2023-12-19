package com.amacom.amacom.dto.auth;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserRequestDTO implements Serializable {

    private static final long serialVersionUID = -2432380621627365836L;

    @NotNull(message = "Field cannot be null")
    private String username;

    @NotNull(message = "Field cannot be null")
    private String email;

    @NotNull(message = "Field cannot be null")
    private String password;

    @NotNull(message = "Field cannot be null")
    private UUID personId;

    private UUID idRol;

}
