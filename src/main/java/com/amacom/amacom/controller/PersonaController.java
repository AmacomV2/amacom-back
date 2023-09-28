package com.amacom.amacom.controller;


import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.mapper.PersonaMapper;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.service.interfaces.IEstadoCivilService;
import com.amacom.amacom.service.interfaces.IGeneroService;
import com.amacom.amacom.service.interfaces.IPersonaService;
import com.amacom.amacom.service.interfaces.ITipoDocumentoService;
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
@RequestMapping("/persona")
public class PersonaController {

    private IPersonaService personaService;

    private ITipoDocumentoService tipoDocumentoService;

    private IGeneroService generoService;

    private IEstadoCivilService estadoCivilService;

    @GetMapping("/getAllPersona")
    public ResponseEntity<List<PersonaDTO>> getAllPersona(){
        List<Persona> personaList = this.personaService.getAll();
        if (personaList == null || personaList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personaList.stream()
                .map(PersonaMapper.INSTANCE::toPersonaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findPersonaById(
            @PathVariable(value = "id") UUID id){
        Persona persona = this.personaService.findPersonaById(id);
        if (persona == null) {
            return new ResponseEntity<>(new PersonaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonaMapper.INSTANCE.toPersonaDTO(persona), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonaDTO> createPersona(
            @Valid @RequestBody PersonaDTO personaDTO){

        Persona persona = PersonaMapper.INSTANCE.toPersona(personaDTO);

        persona.setTipoDocumento(this.tipoDocumentoService.getEntityFromUUID(personaDTO.getIdTipoDocumento()));
        persona.setGenero(this.generoService.getEntityFromUUID(personaDTO.getIdGenero()));
        persona.setEstadoCivil(this.estadoCivilService.getEntityFromUUID(personaDTO.getIdEstadoCivil()));

        var personaBD = this.personaService.createPersona(persona);
        if(personaBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonaMapper.INSTANCE.toPersonaDTO(personaBD));
    }

    @PutMapping
    public ResponseEntity<PersonaDTO> updatePersona(
            @Valid @RequestBody PersonaDTO personaDTO){
        Persona persona = PersonaMapper.INSTANCE.toPersona(personaDTO);

        persona.setTipoDocumento(this.tipoDocumentoService.getEntityFromUUID(personaDTO.getIdTipoDocumento()));
        persona.setGenero(this.generoService.getEntityFromUUID(personaDTO.getIdGenero()));
        persona.setEstadoCivil(this.estadoCivilService.getEntityFromUUID(personaDTO.getIdEstadoCivil()));

        var personaBD = this.personaService.updatePersona(persona);
        if (personaBD == null) {
            return new ResponseEntity<>(new PersonaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonaMapper.INSTANCE.toPersonaDTO(personaBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersona(
            @PathVariable(value = "id") UUID id){
        this.personaService.deletePersonaById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setGeneroService(IGeneroService generoService) {
        this.generoService = generoService;
    }

    @Autowired
    public void setEstadoCivilService(IEstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @Autowired
    public void setTipoDocumentoService(ITipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }
}
