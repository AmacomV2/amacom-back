package com.amacom.amacom.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainHome")
public class MainController {

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/despliegue")
    public String depliegue() {
        return "Correcto";
    }

    // dirección Swagger http://localhost:8080/api/swagger-ui.html#

}
