package com.amacom.amacom.controller;

import com.amacom.amacom.dto.EstadoCivilDTO;
import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.mapper.EstadoCivilMapper;
import com.amacom.amacom.mapper.PersonaMapper;
import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.service.interfaces.IEstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estadoCivil")
public class EstadoCivilController {

    private IEstadoCivilService estadoCivilService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EstadoCivilDTO>> getAll(){
        List<EstadoCivil> estadoCivilList = this.estadoCivilService.getAll();
        if (estadoCivilList == null || estadoCivilList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estadoCivilList.stream()
                .map(EstadoCivilMapper.INSTANCE::toEstadoCivilDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Autowired
    public void setEstadoCivilService(IEstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }
}
