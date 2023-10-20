package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.IndicatorDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.IndicatorMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Indicator;
import com.amacom.amacom.service.interfaces.IIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    private IIndicatorService indicatorService;


    @GetMapping("/{id}")
    public ResponseEntity<IndicatorDTO> findById(
            @PathVariable(value = "id") UUID id){
        Indicator indicator = this.indicatorService.findById(id);
        if (indicator == null) {
            return new ResponseEntity<>(new IndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(IndicatorMapper.INSTANCE.toIndicatorDTO(indicator), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<IndicatorDTO> create(
            @Valid @RequestBody IndicatorDTO indicatorDTO){
        Indicator indicator = IndicatorMapper.INSTANCE.toIndicator(indicatorDTO);
        var indicatorBD = this.indicatorService.create(indicator);
        if(indicatorBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(IndicatorMapper.INSTANCE.toIndicatorDTO(indicatorBD));
    }

    @PutMapping
    public ResponseEntity<IndicatorDTO> update(
            @Valid @RequestBody IndicatorDTO indicatorDTO){
        Indicator indicator = IndicatorMapper.INSTANCE.toIndicator(indicatorDTO);
        var indicatorBD = this.indicatorService.update(indicator);
        if (indicatorBD == null) {
            return new ResponseEntity<>(new IndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(IndicatorMapper.INSTANCE.toIndicatorDTO(indicatorBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.indicatorService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
    @Autowired
    public void setIndicatorService(IIndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

}
