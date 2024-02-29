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

import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.mapper.InstitutionServicePersonMapper;
import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.service.interfaces.IInstitutionServicePersonService;
import com.amacom.amacom.service.interfaces.IInstitutionServiceService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/institutionServicePerson")
public class InstitutionServicePersonController {

        private IInstitutionServicePersonService institutionServicePersonService;

        private IPersonService personService;

        private IInstitutionServiceService institutionServiceService;

        @GetMapping("/consulta")
        public ResponseEntity<Page<InstitutionServicePersonDTO>> findPageable(
                        Pageable pageable,
                        @RequestParam(name = "idInstitutionService") UUID idInstitutionService,
                        @RequestParam(name = "personId", required = false) UUID personId,
                        @RequestParam(name = "query", required = false) String query) {

                var institutionServicePersonPage = this.institutionServicePersonService.findInstitutionServicePerson(
                                idInstitutionService, personId, query,
                                ITools.getPageRequest(pageable, InstitutionServicePersonMapper.getSortKeys()));

                if (institutionServicePersonPage == null || institutionServicePersonPage.getContent().isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(institutionServicePersonPage
                                .map(InstitutionServicePersonMapper.INSTANCE::toInstitutionServicePersonDTO),
                                HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<InstitutionServicePersonDTO> findById(
                        @PathVariable(value = "id") UUID id) {
                InstitutionServicePerson institutionServicePerson = this.institutionServicePersonService.findById(id);
                if (institutionServicePerson == null) {
                        return new ResponseEntity<>(new InstitutionServicePersonDTO(), HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(
                                InstitutionServicePersonMapper.INSTANCE
                                                .toInstitutionServicePersonDTO(institutionServicePerson),
                                HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<InstitutionServicePersonDTO> create(
                        @Valid @RequestBody InstitutionServicePersonDTO institutionServicePersonDTO) {

                InstitutionServicePerson institutionServicePerson = InstitutionServicePersonMapper.INSTANCE
                                .toInstitutionServicePerson(institutionServicePersonDTO);

                institutionServicePerson
                                .setPerson(this.personService
                                                .getPersonFromUUID(institutionServicePersonDTO.getPersonId()));
                institutionServicePerson.setInstitutionService(this.institutionServiceService
                                .getEntityFromUUID(institutionServicePersonDTO.getIdInstitutionService()));

                var institutionServicePersonBD = this.institutionServicePersonService.create(institutionServicePerson);
                if (institutionServicePersonBD == null)
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                return ResponseEntity
                                .ok(InstitutionServicePersonMapper.INSTANCE
                                                .toInstitutionServicePersonDTO(institutionServicePersonBD));
        }

        @PutMapping
        public ResponseEntity<InstitutionServicePersonDTO> update(
                        @Valid @RequestBody InstitutionServicePersonDTO institutionServicePersonDTO) {
                InstitutionServicePerson institutionServicePerson = InstitutionServicePersonMapper.INSTANCE
                                .toInstitutionServicePerson(institutionServicePersonDTO);

                institutionServicePerson
                                .setPerson(this.personService
                                                .getPersonFromUUID(institutionServicePersonDTO.getPersonId()));
                institutionServicePerson.setInstitutionService(this.institutionServiceService
                                .getEntityFromUUID(institutionServicePersonDTO.getIdInstitutionService()));

                var institutionServicePersonBD = this.institutionServicePersonService.update(institutionServicePerson);
                if (institutionServicePersonBD == null) {
                        return new ResponseEntity<>(new InstitutionServicePersonDTO(), HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(
                                InstitutionServicePersonMapper.INSTANCE
                                                .toInstitutionServicePersonDTO(institutionServicePersonBD),
                                HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Boolean> delete(
                        @PathVariable(value = "id") UUID id) {
                this.institutionServicePersonService.deleteById(id);
                return ResponseEntity.ok(Boolean.TRUE);
        }

        @Autowired
        public void setInstitutionServiceService(IInstitutionServiceService institutionServiceService) {
                this.institutionServiceService = institutionServiceService;
        }

        @Autowired
        public void setPersonService(IPersonService personService) {
                this.personService = personService;
        }

        @Autowired
        public void setInstitutionServicePersonService(
                        IInstitutionServicePersonService institutionServicePersonService) {
                this.institutionServicePersonService = institutionServicePersonService;
        }

}
