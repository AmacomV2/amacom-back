package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.ActivityMapper;
import com.amacom.amacom.mapper.FeelingsMapper;
import com.amacom.amacom.model.Activity;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amacom.amacom.dto.IndicatorDTO;
import com.amacom.amacom.mapper.IndicatorMapper;
import com.amacom.amacom.model.Indicator;
import com.amacom.amacom.service.interfaces.IIndicatorService;

@RestController
@RequestMapping("/indicator")
public class IndicatorController {

    private IIndicatorService indicatorService;

    @GetMapping("/{id}")
    public ResponseEntity<IndicatorDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Indicator indicator = this.indicatorService.findById(id);
        if (indicator == null) {
            return new ResponseEntity<>(new IndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(IndicatorMapper.INSTANCE.toIndicatorDTO(indicator), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query)
    {
        Page<Indicator> page = this.indicatorService.search(query,
                ITools.getPageRequest(pageable, FeelingsMapper.getSortKeys()));
        return new ResponseEntity<>(new SuccessDTO(page
                .map(IndicatorMapper.INSTANCE::toIndicatorDTO)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<IndicatorDTO> create(
            @Valid @RequestBody IndicatorDTO indicatorDTO) {
        Indicator indicator = IndicatorMapper.INSTANCE.toIndicator(indicatorDTO);
        var indicatorBD = this.indicatorService.create(indicator);
        if (indicatorBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(IndicatorMapper.INSTANCE.toIndicatorDTO(indicatorBD));
    }

    @PutMapping
    public ResponseEntity<IndicatorDTO> update(
            @Valid @RequestBody IndicatorDTO indicatorDTO) {
        Indicator indicator = IndicatorMapper.INSTANCE.toIndicator(indicatorDTO);
        var indicatorBD = this.indicatorService.update(indicator);
        if (indicatorBD == null) {
            return new ResponseEntity<>(new IndicatorDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(IndicatorMapper.INSTANCE.toIndicatorDTO(indicatorBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.indicatorService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setIndicatorService(IIndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

}
