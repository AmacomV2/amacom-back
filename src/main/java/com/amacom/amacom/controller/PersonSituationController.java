package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonSituationMapper;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.service.interfaces.IUserService;

@RestController
@RequestMapping("/personSituation")
public class PersonSituationController {

    private IPersonSituationService personSituationService;

    private IPersonService personService;

    private IUserService usersService;

    private ISubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        PersonSituation personSituation = this.personSituationService.findById(id);
        if (personSituation == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                new SuccessDTO(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituation)),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody PersonSituationDTO personSituationDTO,
            @RequestHeader(name = "personId", required = false) UUID personId) {
        var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = User.class.cast(authData);
        if (personId == null) {
            personId = user.getPerson().getId();
            personSituationDTO.setCreatedById(user.getId());
        }
        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPerson(this.personService.getPersonFromUUID(personSituationDTO.getPersonId()));
        personSituation.setCreatedBy(this.usersService.getEntityFromUUID(personId));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getSubjectId()));

        var personSituationBD = this.personSituationService.create(personSituation);
        if (personSituationBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity
                .ok(new SuccessDTO(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody PersonSituationDTO personSituationDTO) {
        PersonSituation personSituation = PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);

        personSituation.setPerson(this.personService.getPersonFromUUID(personSituationDTO.getPersonId()));
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getSubjectId()));

        var personSituationBD = this.personSituationService.update(personSituation);
        if (personSituationBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                new SuccessDTO(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.personSituationService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
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
    public void setUsersService(IUserService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

}
