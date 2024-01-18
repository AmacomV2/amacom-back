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
import org.springframework.transaction.annotation.Transactional;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private IPersonService personService;

    private IDocumentTypeService documentTypeService;

    private IGenderService genderService;

    private ICivilStatusService civilStatusService;

    @GetMapping("/query")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {

        try {
            var personPage = this.personService.findPerson(query,
                    ITools.getPageRequest(pageable, PersonMapper.getSortKeys()));
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
    @Transactional
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
    public ResponseEntity<ResponseDTO> createPerson(
            @Valid @RequestBody PersonDTO personDTO) {

        try {
            Person personBD = createPersonMethod(personDTO);
            return ResponseEntity.ok(new SuccessDTO(PersonMapper.INSTANCE.toPersonDTO(personBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public Person createPersonMethod(PersonDTO personDTO) {
        try {
            Person person = PersonMapper.INSTANCE.toPerson(personDTO);
            person.setGender(this.genderService.getEntityFromUUID(personDTO.getGenderId()));
            person.setCivilStatus(this.civilStatusService.getEntityFromUUID(personDTO.getCivilStatusId()));
            person.setDocumentType(this.documentTypeService.getEntityFromUUID(personDTO.getDocumentTypeId()));

            Person personBD = this.personService.createPerson(person);
            return personBD;
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updatePerson(
            @Valid @RequestBody PersonDTO personDTO) {
        try {
            Person person = PersonMapper.INSTANCE.toPerson(personDTO);

            person.setDocumentType(this.documentTypeService.getEntityFromUUID(personDTO.getDocumentTypeId()));
            person.setGender(this.genderService.getEntityFromUUID(personDTO.getGenderId()));
            person.setCivilStatus(this.civilStatusService.getEntityFromUUID(personDTO.getCivilStatusId()));

            var personBD = this.personService.updatePerson(person);
            return new ResponseEntity<>(new SuccessDTO(PersonMapper.INSTANCE.toPersonDTO(personBD)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePerson(
            @PathVariable(value = "id") UUID id) {
        try {
            this.personService.deletePersonById(id);
            return ResponseEntity.ok(new SuccessDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public void setCivilStatusService(ICivilStatusService civilStatusService) {
        this.civilStatusService = civilStatusService;
    }

    @Autowired
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
}
