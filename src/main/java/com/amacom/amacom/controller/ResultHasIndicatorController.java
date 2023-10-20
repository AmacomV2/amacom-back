package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.ResultHasIndicatorDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.ResultHasIndicatorMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.ResultHasIndicator;
import com.amacom.amacom.service.interfaces.IIndicatorService;
import com.amacom.amacom.service.interfaces.IResultHasIndicatorService;
import com.amacom.amacom.service.interfaces.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/resultHasIndicator")
public class ResultHasIndicatorController {

    private IResultHasIndicatorService resultHasIndicatorService;

    private IResultService resultService;

    private IIndicatorService indicatorService;


    @GetMapping("/{id}")
    public ResponseEntity<ResultHasIndicatorDTO> findById(
            @PathVariable(value = "id") UUID id){
        ResultHasIndicator resultHasIndicator = this.resultHasIndicatorService.findById(id);
        if (resultHasIndicator == null) {
            return new ResponseEntity<>(new ResultHasIndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultHasIndicatorMapper.INSTANCE.toResultHasIndicatorDTO(resultHasIndicator), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResultHasIndicatorDTO> create(
            @Valid @RequestBody ResultHasIndicatorDTO resultHasIndicatorDTO){

        ResultHasIndicator resultHasIndicator = ResultHasIndicatorMapper.INSTANCE.toResultHasIndicator(resultHasIndicatorDTO);

        resultHasIndicator.setResult(this.resultService.getEntityFromUUID(resultHasIndicatorDTO.getIdResult()));
        resultHasIndicator.setIndicator(this.indicatorService.getEntityFromUUID(resultHasIndicatorDTO.getIdIndicator()));

        var resultHasIndicatorBD = this.resultHasIndicatorService.create(resultHasIndicator);
        if(resultHasIndicatorBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ResultHasIndicatorMapper.INSTANCE.toResultHasIndicatorDTO(resultHasIndicatorBD));
    }

    @PutMapping
    public ResponseEntity<ResultHasIndicatorDTO> update(
            @Valid @RequestBody ResultHasIndicatorDTO resultHasIndicatorDTO){
        ResultHasIndicator resultHasIndicator = ResultHasIndicatorMapper.INSTANCE.toResultHasIndicator(resultHasIndicatorDTO);

        resultHasIndicator.setResult(this.resultService.getEntityFromUUID(resultHasIndicatorDTO.getIdResult()));
        resultHasIndicator.setIndicator(this.indicatorService.getEntityFromUUID(resultHasIndicatorDTO.getIdIndicator()));

        var resultHasIndicatorBD = this.resultHasIndicatorService.update(resultHasIndicator);
        if (resultHasIndicatorBD == null) {
            return new ResponseEntity<>(new ResultHasIndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultHasIndicatorMapper.INSTANCE.toResultHasIndicatorDTO(resultHasIndicatorBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.resultHasIndicatorService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setIndicatorService(IIndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @Autowired
    public void setResultService(IResultService resultService) {
        this.resultService = resultService;
    }

    @Autowired
    public void setResultHasIndicatorService(IResultHasIndicatorService resultHasIndicatorService) {
        this.resultHasIndicatorService = resultHasIndicatorService;
    }


}
