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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonSituationHasFeelingsDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonSituationHasFeelingsMapper;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import com.amacom.amacom.service.interfaces.IFeelingsService;
import com.amacom.amacom.service.interfaces.IPersonSituationHasFeelingsService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;

@RestController
@RequestMapping("/personSituationHasFeelings")
public class PersonSituationHasFeelingsController {

        private IPersonSituationHasFeelingsService personSituationHasFeelingsService;

        private IPersonSituationService personSituationService;

        private IFeelingsService feelingsService;

        @GetMapping("/{id}")
        public ResponseEntity<ResponseDTO> findById(
                        @PathVariable(value = "id") UUID id) {
                PersonSituationHasFeelings personSituationHasFeelings = this.personSituationHasFeelingsService
                                .findById(id);
                if (personSituationHasFeelings == null) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(
                                new SuccessDTO(PersonSituationHasFeelingsMapper.INSTANCE
                                                .toPersonSituationHasFeelingsDTO(personSituationHasFeelings)),
                                HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<ResponseDTO> create(
                        @Valid @RequestBody PersonSituationHasFeelingsDTO personSituationHasFeelingsDTO) {

                PersonSituationHasFeelings personSituationHasFeelings = PersonSituationHasFeelingsMapper.INSTANCE
                                .toPersonSituationHasFeelings(personSituationHasFeelingsDTO);

                personSituationHasFeelings.setPersonSituation(
                                this.personSituationService.getEntityFromUUID(
                                                personSituationHasFeelingsDTO.getPersonSituationId()));
                personSituationHasFeelings
                                .setFeelings(this.feelingsService
                                                .getEntityFromUUID(personSituationHasFeelingsDTO.getIdFeelings()));

                var personSituationHasFeelingsBD = this.personSituationHasFeelingsService
                                .create(personSituationHasFeelings);
                if (personSituationHasFeelingsBD == null)
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                return ResponseEntity.ok(new SuccessDTO(PersonSituationHasFeelingsMapper.INSTANCE
                                .toPersonSituationHasFeelingsDTO(personSituationHasFeelingsBD)));
        }

        @PutMapping
        public ResponseEntity<ResponseDTO> update(
                        @Valid @RequestBody PersonSituationHasFeelingsDTO personSituationHasFeelingsDTO) {
                PersonSituationHasFeelings personSituationHasFeelings = PersonSituationHasFeelingsMapper.INSTANCE
                                .toPersonSituationHasFeelings(personSituationHasFeelingsDTO);

                personSituationHasFeelings.setPersonSituation(
                                this.personSituationService.getEntityFromUUID(
                                                personSituationHasFeelingsDTO.getPersonSituationId()));
                personSituationHasFeelings
                                .setFeelings(this.feelingsService
                                                .getEntityFromUUID(personSituationHasFeelingsDTO.getIdFeelings()));

                var personSituationHasFeelingsBD = this.personSituationHasFeelingsService
                                .update(personSituationHasFeelings);
                if (personSituationHasFeelingsBD == null) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return ResponseEntity.ok(new SuccessDTO(PersonSituationHasFeelingsMapper.INSTANCE
                                .toPersonSituationHasFeelingsDTO(personSituationHasFeelingsBD)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseDTO> delete(
                        @PathVariable(value = "id") UUID id) {
                this.personSituationHasFeelingsService.deleteById(id);
                return ResponseEntity.ok(new SuccessDTO());
        }

        @Autowired
        public void setPersonSituationService(IPersonSituationService personSituationService) {
                this.personSituationService = personSituationService;
        }

        @Autowired
        public void setFeelingsService(IFeelingsService feelingsService) {
                this.feelingsService = feelingsService;
        }

        @Autowired
        public void setPersonSituationHasFeelingsService(
                        IPersonSituationHasFeelingsService personSituationHasFeelingsService) {
                this.personSituationHasFeelingsService = personSituationHasFeelingsService;
        }

}
