package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.PersonSituationMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/personSituation")
public class PersonSituationController {

    private IPersonSituationService personSituationService;

    private IPersonaService personaService;

    private IUsuarioService usuarioService;

    private ISubjectService subjectService;

    private ITipoSituacionService tipoSituacionService;


    @GetMapping("/{id}")
    public ResponseEntity<PersonSituationDTO> findById(
            @PathVariable(value = "id") UUID id){
        PersonSituation personSituation = this.personSituationService.findById(id);
        if (personSituation == null) {
            return new ResponseEntity<>(new PersonSituationDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituation), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonSituationDTO> create(
            @Valid @RequestBody PersonSituationDTO personSituationDTO,
            @RequestHeader("idUsuario") UUID idUsuario){

        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPersona(this.personaService.getPersonaFromUUID(personSituationDTO.getIdPersona()));
        personSituation.setUsuario(this.usuarioService.getEntityFromUUID(idUsuario));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getIdSubject()));
        personSituation.setTipoSituacion(this.tipoSituacionService.getEntityFromUUID(personSituationDTO.getIdTipoSituacion()));

        var personSituationBD = this.personSituationService.create(personSituation);
        if(personSituationBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD));
    }

    @PutMapping
    public ResponseEntity<PersonSituationDTO> update(
            @Valid @RequestBody PersonSituationDTO personSituationDTO){
        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPersona(this.personaService.getPersonaFromUUID(personSituationDTO.getIdPersona()));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getIdSubject()));
        personSituation.setTipoSituacion(this.tipoSituacionService.getEntityFromUUID(personSituationDTO.getIdTipoSituacion()));

        var personSituationBD = this.personSituationService.update(personSituation);
        if (personSituationBD == null) {
            return new ResponseEntity<>(new PersonSituationDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.personSituationService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setPersonSituationService(IPersonSituationService personSituationService) {
        this.personSituationService = personSituationService;
    }

    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setTipoSituacionService(ITipoSituacionService tipoSituacionService) {
        this.tipoSituacionService = tipoSituacionService;
    }


}
