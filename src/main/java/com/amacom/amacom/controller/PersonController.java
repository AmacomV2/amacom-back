package com.amacom.amacom.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonMapper;
import com.amacom.amacom.model.Person;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.ICivilStatusService;
import com.amacom.amacom.service.interfaces.IDocumentTypeService;
import com.amacom.amacom.service.interfaces.IGenderService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.util.ErrorCodes;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/person")
public class PersonController {

    private IPersonService personService;

    private IDocumentTypeService documentTypeService;

    private IGenderService genderService;

    private ICivilStatusService statusCivilService;

    @GetMapping("/query")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {

        try {
            var personPage = this.personService.findPerson(query,
                    ITools.getPageRequest(pageable, PersonMapper.getClavesToSort()));
            return new ResponseEntity<>(new SuccessDTO(personPage
                    .map(PersonMapper.INSTANCE::toPersonDTO)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ERROR_QUERY_DB), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllPersons")
    public ResponseEntity<ResponseDTO> getAllPersons() {
        try {
            List<Person> personList = this.personService.getAll();
            if (personList.isEmpty()) {
                return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new SuccessDTO(personList.stream()
                    .map(PersonMapper.INSTANCE::toPersonDTO).collect(Collectors.toList())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(ErrorCodes.ERROR_QUERY_DB), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> findPersonById(
            @RequestParam(value = "id", required = false) @Nullable UUID id) {
        try {
            UUID personId = id;
            if (id == null) {
                var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                var user = User.class.cast(authData);
                return new ResponseEntity<>(new SuccessDTO(PersonMapper.INSTANCE.toPersonDTO(user.getPerson())),
                        HttpStatus.OK);

            }
            Person person = this.personService.findPersonById(personId);

            if (person == null) {
                return new ResponseEntity<>(new ErrorDTO("Person not fount."), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new SuccessDTO(PersonMapper.INSTANCE.toPersonDTO(person)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDTO> createPerson(
            @Valid @RequestBody PersonDTO personDTO) {

        Person person = PersonMapper.INSTANCE.toPerson(personDTO);

        person.setDocumentType(this.documentTypeService.getEntityFromUUID(personDTO.getIdDocumentType()));
        person.setGender(this.genderService.getEntityFromUUID(personDTO.getIdGenero()));
        person.setStatusCivil(this.statusCivilService.getEntityFromUUID(personDTO.getIdStatusCivil()));

        var personBD = this.personService.createPerson(person);
        if (personBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonMapper.INSTANCE.toPersonDTO(personBD));
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(
            @Valid @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toPerson(personDTO);

        person.setDocumentType(this.documentTypeService.getEntityFromUUID(personDTO.getIdDocumentType()));
        person.setGender(this.genderService.getEntityFromUUID(personDTO.getIdGenero()));
        person.setStatusCivil(this.statusCivilService.getEntityFromUUID(personDTO.getIdStatusCivil()));

        var personBD = this.personService.updatePerson(person);
        if (personBD == null) {
            return new ResponseEntity<>(new PersonDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonMapper.INSTANCE.toPersonDTO(personBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePerson(
            @PathVariable(value = "id") UUID id) {
        this.personService.deletePersonById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setGenderService(IGenderService genderService) {
        this.genderService = genderService;
    }

    @Autowired
    public void setStatusCivilService(ICivilStatusService statusCivilService) {
        this.statusCivilService = statusCivilService;
    }

    @Autowired
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
}
