package com.amacom.amacom.dto.auth;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponseDTO implements Serializable {

    private static final long serialVersionUID = -289134949499480272L;

    String accessToken;
    String refreshToken;

}
