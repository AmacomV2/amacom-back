package com.amacom.amacom.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = -3180914187196092198L;

    private String username;

    private String email;

    private String password;

    private UUID idPersona;

    private UUID idRol;
}
