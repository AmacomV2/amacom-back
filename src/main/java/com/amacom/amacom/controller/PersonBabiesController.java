package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonBabiesDTO;
import com.amacom.amacom.mapper.PersonBabiesMapper;
import com.amacom.amacom.model.PersonBabies;
import com.amacom.amacom.service.interfaces.IPersonBabiesService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/personBabies")
public class PersonBabiesController {

    private IPersonBabiesService personBabiesService;

    private IPersonService personService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<PersonBabiesDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "personId", required = false) UUID personId,
            @RequestParam(name = "query", required = false) String query) {

        var personBabiesPage = this.personBabiesService.findPersonBabies(personId, query,
                ITools.getPageRequest(pageable, PersonBabiesMapper.getClavesToSort()));

        if (personBabiesPage == null || personBabiesPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personBabiesPage
                .map(PersonBabiesMapper.INSTANCE::toPersonBabiesDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonBabiesDTO> findById(
            @PathVariable(value = "id") UUID id) {
        PersonBabies personBabies = this.personBabiesService.findById(id);
        if (personBabies == null) {
            return new ResponseEntity<>(new PersonBabiesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonBabiesMapper.INSTANCE.toPersonBabiesDTO(personBabies), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonBabiesDTO> create(
            @Valid @RequestBody PersonBabiesDTO personBabiesDTO) {

        PersonBabies personBabies = PersonBabiesMapper.INSTANCE.toPersonBabies(personBabiesDTO);

        personBabies.setParent(this.personService.getPersonFromUUID(personBabiesDTO.getIdParent()));
        personBabies.setChild(this.personService.getPersonFromUUID(personBabiesDTO.getIdChild()));

        var personBabiesBD = this.personBabiesService.create(personBabies);
        if (personBabiesBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonBabiesMapper.INSTANCE.toPersonBabiesDTO(personBabiesBD));
    }

    @PutMapping
    public ResponseEntity<PersonBabiesDTO> update(
            @Valid @RequestBody PersonBabiesDTO personBabiesDTO) {
        PersonBabies personBabies = PersonBabiesMapper.INSTANCE.toPersonBabies(personBabiesDTO);

        personBabies.setParent(this.personService.getPersonFromUUID(personBabiesDTO.getIdParent()));
        personBabies.setChild(this.personService.getPersonFromUUID(personBabiesDTO.getIdChild()));

        var personBabiesBD = this.personBabiesService.update(personBabies);
        if (personBabiesBD == null) {
            return new ResponseEntity<>(new PersonBabiesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonBabiesMapper.INSTANCE.toPersonBabiesDTO(personBabiesBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.personBabiesService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setpersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setPersonBabiesService(IPersonBabiesService personBabiesService) {
        this.personBabiesService = personBabiesService;
    }
}
