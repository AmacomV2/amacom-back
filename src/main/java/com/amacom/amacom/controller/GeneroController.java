package com.amacom.amacom.controller;

import com.amacom.amacom.dto.EstadoCivilDTO;
import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.mapper.EstadoCivilMapper;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.PersonaMapper;
import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.service.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private IGeneroService generoService;


    @GetMapping("/getAll")
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<Genero> generoList = this.generoService.getAll();
        if (generoList == null || generoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(generoList.stream()
                .map(GeneroMapper.INSTANCE::toGeneroDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> findById(
            @PathVariable(value = "id") UUID id){
        Genero genero = this.generoService.findById(id);
        if (genero == null) {
            return new ResponseEntity<>(new GeneroDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(GeneroMapper.INSTANCE.toGeneroDTO(genero), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<GeneroDTO> create(
            @Valid @RequestBody GeneroDTO generoDTO){
        Genero genero = GeneroMapper.INSTANCE.toGenero(generoDTO);
        var generoBD = this.generoService.create(genero);
        if(generoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(GeneroMapper.INSTANCE.toGeneroDTO(generoBD));
    }

    @PutMapping
    public ResponseEntity<GeneroDTO> update(
            @Valid @RequestBody GeneroDTO generoDTO){
        Genero genero = GeneroMapper.INSTANCE.toGenero(generoDTO);
        var generoBD = this.generoService.update(genero);
        if (generoBD == null) {
            return new ResponseEntity<>(new GeneroDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(GeneroMapper.INSTANCE.toGeneroDTO(generoBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.generoService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setGeneroService(IGeneroService generoService) {
        this.generoService = generoService;
    }
}
