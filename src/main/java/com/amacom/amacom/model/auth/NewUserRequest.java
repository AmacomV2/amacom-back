package com.amacom.amacom.model.auth;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest implements Serializable {

    private static final long serialVersionUID = -3180914187196092198L;

    private String username;

    private String email;

    private String password;

    private UUID personId;

    private UUID idRol;
}
