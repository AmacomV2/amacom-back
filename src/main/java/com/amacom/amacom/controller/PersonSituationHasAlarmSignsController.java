package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.PersonSituationHasAlarmSignsDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.PersonSituationHasAlarmSignsMapper;
import com.amacom.amacom.model.EAlarmSignType;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import com.amacom.amacom.service.interfaces.IAlarmSignService;
import com.amacom.amacom.service.interfaces.IPersonSituationHasAlarmSignsService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/personSituationHasAlarmSigns")
public class PersonSituationHasAlarmSignsController {

        private IPersonSituationHasAlarmSignsService personSituationHasAlarmSignsService;

        private IPersonSituationService personSituationService;

        private IAlarmSignService alarmSignService;

        @GetMapping("/{id}")
        public ResponseEntity<ResponseDTO> findById(
                        @PathVariable(value = "id") UUID id) {
                PersonSituationHasAlarmSigns personSituationHasAlarmSigns = this.personSituationHasAlarmSignsService
                                .findById(id);
                if (personSituationHasAlarmSigns == null) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return ResponseEntity.ok(new SuccessDTO(PersonSituationHasAlarmSignsMapper.INSTANCE
                                .toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSigns)));
        }

        @GetMapping("/search")
        public ResponseEntity<ResponseDTO> search(
                        Pageable pageable,
                        @RequestParam(name = "situationId", required = true) @Nullable UUID situationId,
                        @RequestParam(name = "query", required = false, defaultValue = "") String query,
                        @RequestParam(name = "type", required = false) @Nullable EAlarmSignType type) {
                try {
                        Page<PersonSituationHasAlarmSigns> page = this.personSituationHasAlarmSignsService
                                        .findAlarmSign(type, query,
                                                        ITools.getPageRequest(pageable,
                                                                        PersonSituationHasAlarmSignsMapper
                                                                                        .getSortKeys()),
                                                        situationId);
                        return new ResponseEntity<>(new SuccessDTO(page
                                        .map(PersonSituationHasAlarmSignsMapper.INSTANCE::toPersonSituationHasAlarmSignsDTO)),
                                        HttpStatus.OK);
                } catch (Exception e) {
                        return new ResponseEntity<>(new ErrorDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);

                }
        }

        @PostMapping("/create")
        public ResponseEntity<ResponseDTO> create(
                        @Valid @RequestBody PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO) {

                PersonSituationHasAlarmSigns personSituationHasAlarmSigns = PersonSituationHasAlarmSignsMapper.INSTANCE
                                .toPersonSituationHasAlarmSigns(personSituationHasAlarmSignsDTO);

                personSituationHasAlarmSigns.setPersonSituation(
                                this.personSituationService.getEntityFromUUID(
                                                personSituationHasAlarmSignsDTO.getPersonSituationId()));
                personSituationHasAlarmSigns.setAlarmSign(
                                this.alarmSignService
                                                .getEntityFromUUID(personSituationHasAlarmSignsDTO.getAlarmSignId()));

                var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsService
                                .create(personSituationHasAlarmSigns);
                if (personSituationHasAlarmSignsBD == null)
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                return ResponseEntity.ok(new SuccessDTO(PersonSituationHasAlarmSignsMapper.INSTANCE
                                .toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSignsBD)));
        }

        @PutMapping
        public ResponseEntity<ResponseDTO> update(
                        @Valid @RequestBody PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO) {
                PersonSituationHasAlarmSigns personSituationHasAlarmSigns = PersonSituationHasAlarmSignsMapper.INSTANCE
                                .toPersonSituationHasAlarmSigns(personSituationHasAlarmSignsDTO);

                personSituationHasAlarmSigns.setPersonSituation(
                                this.personSituationService.getEntityFromUUID(
                                                personSituationHasAlarmSignsDTO.getPersonSituationId()));
                personSituationHasAlarmSigns.setAlarmSign(
                                this.alarmSignService
                                                .getEntityFromUUID(personSituationHasAlarmSignsDTO.getAlarmSignId()));

                var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsService
                                .update(personSituationHasAlarmSigns);
                if (personSituationHasAlarmSignsBD == null) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return ResponseEntity.ok(new SuccessDTO(PersonSituationHasAlarmSignsMapper.INSTANCE
                                .toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSignsBD)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseDTO> delete(
                        @PathVariable(value = "id") UUID id) {
                this.personSituationHasAlarmSignsService.deleteById(id);
                return ResponseEntity.ok(new SuccessDTO());
        }

        @Autowired
        public void setPersonSituationService(IPersonSituationService personSituationService) {
                this.personSituationService = personSituationService;
        }

        @Autowired
        public void setAlarmSignService(IAlarmSignService alarmSignService) {
                this.alarmSignService = alarmSignService;
        }

        @Autowired
        public void setPersonSituationHasAlarmSignsService(
                        IPersonSituationHasAlarmSignsService personSituationHasAlarmSignsService) {
                this.personSituationHasAlarmSignsService = personSituationHasAlarmSignsService;
        }

}
