package com.amacom.amacom.controller;

import com.amacom.amacom.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @GetMapping("/alertaConsulta")
    public ResponseEntity<List<EAlertaConsulta>> obtenerEAlertaConsulta() {
        List<EAlertaConsulta> estados = Arrays.asList(EAlertaConsulta.values());
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/estadoConsulta")
    public ResponseEntity<List<EEstadoConsulta>> obtenerEEstadoConsulta() {
        List<EEstadoConsulta> estados = Arrays.asList(EEstadoConsulta.values());
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/estadoEvento")
    public ResponseEntity<List<EEstadoEvento>> obtenerEEstadoEvento() {
        List<EEstadoEvento> estados = Arrays.asList(EEstadoEvento.values());
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/gradoAfectacion")
    public ResponseEntity<List<EGradoAfectacion>> obtenerEGradoAfectacion() {
        List<EGradoAfectacion> estados = Arrays.asList(EGradoAfectacion.values());
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/puntaje")
    public ResponseEntity<List<EPuntaje>> obtenerEPuntaje() {
        List<EPuntaje> estados = Arrays.asList(EPuntaje.values());
        return ResponseEntity.ok(estados);
    }
}
