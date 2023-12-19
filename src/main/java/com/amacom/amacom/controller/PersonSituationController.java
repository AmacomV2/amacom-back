package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonSituationMapper;
import com.amacom.amacom.mapper.PersonSituationMapperVar;
import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/personSituation")
public class PersonSituationController {

    private IPersonSituationService personSituationService;

    private IPersonService personService;

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

        personSituation.setPerson(this.personService.getPersonFromUUID(personId));
        personSituation.setCreatedBy(user);
        personSituation.setSubject(this.subjectService.getEntityFromUUID(personSituationDTO.getSubjectId()));

        var personSituationBD = this.personSituationService.create(personSituation);
        if (personSituationBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity
                .ok(new SuccessDTO(PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituationBD)));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> search(
            Pageable pageable,
            @RequestParam(name = "personId", required = false) @Nullable UUID personId,
            @RequestParam(name = "consultationAlert", required = false) @Nullable EConsultationAlert consultationAlert,
            @RequestParam(name = "consultationStatus", required = false) @Nullable EConsultationStatus consultationStatus,
            @RequestParam(name = "query", required = false) String query) {
        try {
            if (personId == null) {
                var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = User.class.cast(authData);
                personId = user.getPerson().getId();
            }

            Page<PersonSituation> page = this.personSituationService.search(consultationAlert, consultationStatus,
                    query, personId,
                    ITools.getPageRequest(pageable, PersonSituationMapper.getClavesToSort()));
            return new ResponseEntity<>(new SuccessDTO(page
                    .map(PersonSituationMapperVar.INSTANCE::toPersonSituationDTO)), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

}
