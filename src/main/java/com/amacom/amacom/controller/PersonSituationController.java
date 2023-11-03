package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.mapper.PersonSituationMapper;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import com.amacom.amacom.service.interfaces.ISituationTypeService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.service.interfaces.IUserService;

@RestController
@RequestMapping("/personSituation")
public class PersonSituationController {

    private IPersonSituationService personSituationService;

    private IPersonService personService;

    private IUserService usuarioService;

    private ISubjectService subjectService;

    private ISituationTypeService situationTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonSituationDTO> findById(
            @PathVariable(value = "id") UUID id) {
        PersonSituation personSituation = this.personSituationService.findById(id);
        if (personSituation == null) {
            return new ResponseEntity<>(new PersonSituationDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituation),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonSituationDTO> create(
            @Valid @RequestBody PersonSituationDTO personSituationDTO,
            @RequestHeader("userId") UUID userId) {

        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPerson(this.personService.getPersonFromUUID(personSituationDTO.getPersonId()));
        personSituation.setUsuario(this.usuarioService.getEntityFromUUID(userId));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getSubjectId()));
        personSituation
                .setSituationType(this.situationTypeService.getEntityFromUUID(personSituationDTO.getSituationTypeId()));

        var personSituationBD = this.personSituationService.create(personSituation);
        if (personSituationBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD));
    }

    @PutMapping
    public ResponseEntity<PersonSituationDTO> update(
            @Valid @RequestBody PersonSituationDTO personSituationDTO) {
        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPerson(this.personService.getPersonFromUUID(personSituationDTO.getPersonId()));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getSubjectId()));
        personSituation
                .setSituationType(this.situationTypeService.getEntityFromUUID(personSituationDTO.getSituationTypeId()));

        var personSituationBD = this.personSituationService.update(personSituation);
        if (personSituationBD == null) {
            return new ResponseEntity<>(new PersonSituationDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.personSituationService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setPersonSituationService(IPersonSituationService personSituationService) {
        this.personSituationService = personSituationService;
    }

    @Autowired
    public void setUsuarioService(IUserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void situationTypeService(ISituationTypeService situationTypeService) {
        this.situationTypeService = situationTypeService;
    }

}
