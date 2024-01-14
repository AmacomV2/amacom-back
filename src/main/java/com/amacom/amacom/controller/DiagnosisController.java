package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.mapper.FeelingsMapper;
import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.amacom.amacom.dto.DiagnosisDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.DiagnosisMapper;
import com.amacom.amacom.model.Diagnosis;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    private IDiagnosisService diagnosisService;

    private IPersonSituationService personSituationService;

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Diagnosis diagnosis = this.diagnosisService.findById(id);
        if (diagnosis == null) {
            return new ResponseEntity<>(new DiagnosisDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DiagnosisMapper.INSTANCE.toDiagnosisDTO(diagnosis), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "situationId", required = false) UUID situacionId)
    {
            Page<Diagnosis> page = this.diagnosisService.search(query,
                    situacionId,
                    ITools.getPageRequest(pageable, FeelingsMapper.getSortKeys()));
            return new ResponseEntity<>(new SuccessDTO(page
                    .map(DiagnosisMapper.INSTANCE::toDiagnosisDTO)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody DiagnosisDTO diagnosisDTO) {

        Diagnosis diagnosis = DiagnosisMapper.INSTANCE.toDiagnosis(diagnosisDTO);
        PersonSituation personSituation = this.personSituationService
                .getEntityFromUUID(diagnosisDTO.getPersonSituationId());
        /// Set created by for current user

        diagnosis
                .setPersonSituation(personSituation);
        diagnosis
                .setCreatedBy(User.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));

        var diagnosisBD = this.diagnosisService.create(diagnosis);
        personSituation.setCurrentDiagnosis(diagnosis);
        /// Set current diagnosis to the new one
        this.personSituationService.update(personSituation);
        if (diagnosisBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(DiagnosisMapper.INSTANCE.toDiagnosisDTO(diagnosisBD)));
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody DiagnosisDTO diagnosisDTO) {

        Diagnosis diagnosis = DiagnosisMapper.INSTANCE.toDiagnosis(diagnosisDTO);

        diagnosis
                .setPersonSituation(this.personSituationService.getEntityFromUUID(diagnosisDTO.getPersonSituationId()));

        var diagnosisBD = this.diagnosisService.update(diagnosis);
        if (diagnosisBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new SuccessDTO(DiagnosisMapper.INSTANCE.toDiagnosisDTO(diagnosisBD)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.diagnosisService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setDiagnosisService(IDiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Autowired
    public void setPersonSituationService(IPersonSituationService personSituationService) {
        this.personSituationService = personSituationService;
    }

}
