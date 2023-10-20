package com.amacom.amacom.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/mainHome")
public class MainController {

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/despliegue")
    public String depliegue(){
        return "Correcto";
    }

    //dirección Swagger http://localhost:8080/api/swagger-ui.html#

}
