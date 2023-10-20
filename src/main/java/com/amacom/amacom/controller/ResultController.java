package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.ResultDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.ResultMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Result;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/result")
public class ResultController {

    private IResultService resultService;

    private IDiagnosisService diagnosisService;


    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> findById(
            @PathVariable(value = "id") UUID id){
        Result result =  this.resultService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(new ResultDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultMapper.INSTANCE.toResultDTO(result), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResultDTO> create(
            @Valid @RequestBody ResultDTO resultDTO){

        Result result =  ResultMapper.INSTANCE.toResult(resultDTO);

        result.setDiagnosis(this.diagnosisService.getEntityFromUUID(resultDTO.getIdDiagnosis()));

        var resultBD = this.resultService.create(result);
        if(resultBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ResultMapper.INSTANCE.toResultDTO(resultBD));
    }

    @PutMapping
    public ResponseEntity<ResultDTO> update(
            @Valid @RequestBody ResultDTO resultDTO){
        Result result =  ResultMapper.INSTANCE.toResult(resultDTO);

        result.setDiagnosis(this.diagnosisService.getEntityFromUUID(resultDTO.getIdDiagnosis()));

        var resultBD = this.resultService.update(result);
        if (resultBD == null) {
            return new ResponseEntity<>(new ResultDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultMapper.INSTANCE.toResultDTO(resultBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
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
