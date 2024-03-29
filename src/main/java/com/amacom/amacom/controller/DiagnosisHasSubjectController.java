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

import com.amacom.amacom.dto.DiagnosisHasSubjectDTO;
import com.amacom.amacom.mapper.DiagnosisHasSubjectMapper;
import com.amacom.amacom.model.DiagnosisHasSubject;
import com.amacom.amacom.service.interfaces.IDiagnosisHasSubjectService;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.ISubjectService;

@RestController
@RequestMapping("/diagnosisHasSubject")
public class DiagnosisHasSubjectController {
    private IDiagnosisHasSubjectService diagnosisHasSubjectService;

    private IDiagnosisService diagnosisService;

    private ISubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisHasSubjectDTO> findById(
            @PathVariable(value = "id") UUID id) {
        DiagnosisHasSubject diagnosisHasSubject = this.diagnosisHasSubjectService.findById(id);
        if (diagnosisHasSubject == null) {
            return new ResponseEntity<>(new DiagnosisHasSubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DiagnosisHasSubjectMapper.INSTANCE.toDiagnosisHasSubjectDTO(diagnosisHasSubject),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DiagnosisHasSubjectDTO> create(
            @Valid @RequestBody DiagnosisHasSubjectDTO diagnosisHasSubjectDTO) {

        DiagnosisHasSubject diagnosisHasSubject = DiagnosisHasSubjectMapper.INSTANCE
                .toDiagnosisHasSubject(diagnosisHasSubjectDTO);

        diagnosisHasSubject
                .setDiagnosis(this.diagnosisService.getEntityFromUUID(diagnosisHasSubjectDTO.getDiagnosisId()));
        diagnosisHasSubject.setSubject(this.subjectService.getEntityFromUUID(diagnosisHasSubjectDTO.getSubjectId()));

        var diagnosisHasSubjectBD = this.diagnosisHasSubjectService.create(diagnosisHasSubject);
        if (diagnosisHasSubjectBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(DiagnosisHasSubjectMapper.INSTANCE.toDiagnosisHasSubjectDTO(diagnosisHasSubjectBD));
    }

    @PutMapping
    public ResponseEntity<DiagnosisHasSubjectDTO> update(
            @Valid @RequestBody DiagnosisHasSubjectDTO diagnosisHasSubjectDTO) {
        DiagnosisHasSubject diagnosisHasSubject = DiagnosisHasSubjectMapper.INSTANCE
                .toDiagnosisHasSubject(diagnosisHasSubjectDTO);

        diagnosisHasSubject
                .setDiagnosis(this.diagnosisService.getEntityFromUUID(diagnosisHasSubjectDTO.getDiagnosisId()));
        diagnosisHasSubject.setSubject(this.subjectService.getEntityFromUUID(diagnosisHasSubjectDTO.getSubjectId()));

        var diagnosisHasSubjectBD = this.diagnosisHasSubjectService.update(diagnosisHasSubject);
        if (diagnosisHasSubjectBD == null) {
            return new ResponseEntity<>(new DiagnosisHasSubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DiagnosisHasSubjectMapper.INSTANCE.toDiagnosisHasSubjectDTO(diagnosisHasSubjectBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.diagnosisHasSubjectService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setDiagnosisService(IDiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Autowired
    public void setDiagnosisHasSubjectService(IDiagnosisHasSubjectService diagnosisHasSubjectService) {
        this.diagnosisHasSubjectService = diagnosisHasSubjectService;
    }

}
