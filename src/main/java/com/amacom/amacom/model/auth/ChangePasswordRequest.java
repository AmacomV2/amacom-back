package com.amacom.amacom.model.auth;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest implements Serializable {

    private static final long serialVersionUID = 4545569535034967314L;

    private UUID id;
    private String newPassword;

    public static ChangePasswordRequest buildRequest(UUID id, String password) {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setId(id);
        request.setNewPassword(password);
        return request;
    }
}
