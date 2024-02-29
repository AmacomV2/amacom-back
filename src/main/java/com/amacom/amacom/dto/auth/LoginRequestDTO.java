package com.amacom.amacom.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = -5945021442138206744L;

    @NotNull(message = "Field cannot be null")
    private String username;

    @NotNull(message = "Field cannot be null")
    private String password;
}
