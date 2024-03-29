package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.amacom.amacom.dto.ResultDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.ResultMapper;
import com.amacom.amacom.model.Result;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.IResultService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/result")
public class ResultController {

    private IResultService resultService;

    private IDiagnosisService diagnosisService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "diagnosisId", required = true) UUID diagnosisId) {

        try {
            var subjectPage = this.resultService.findResults(diagnosisId,
                    ITools.getPageRequest(pageable, ResultMapper.getSortKeys()));

            return ResponseEntity.ok(new SuccessDTO(subjectPage
                    .map(ResultMapper.INSTANCE::toResultDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Result result = this.resultService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(new ResultDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultMapper.INSTANCE.toResultDTO(result), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDTO> create(
            @Valid @RequestBody ResultDTO resultDTO) {

        Result result = ResultMapper.INSTANCE.toResult(resultDTO);

        result.setDiagnosis(this.diagnosisService.getEntityFromUUID(resultDTO.getDiagnosisId()));

        var resultBD = this.resultService.create(result);
        if (resultBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ResultMapper.INSTANCE.toResultDTO(resultBD));
    }

    @PutMapping
    public ResponseEntity<ResultDTO> update(
            @Valid @RequestBody ResultDTO resultDTO) {
        Result result = ResultMapper.INSTANCE.toResult(resultDTO);

        result.setDiagnosis(this.diagnosisService.getEntityFromUUID(resultDTO.getDiagnosisId()));

        var resultBD = this.resultService.update(result);
        if (resultBD == null) {
            return new ResponseEntity<>(new ResultDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultMapper.INSTANCE.toResultDTO(resultBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.resultService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setDiagnosisService(IDiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Autowired
    public void setResultService(IResultService resultService) {
        this.resultService = resultService;
    }

}
