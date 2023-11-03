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

import com.amacom.amacom.dto.FeelingsDTO;
import com.amacom.amacom.mapper.FeelingsMapper;
import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.service.interfaces.IFeelingsService;

@RestController
@RequestMapping("/feelings")
public class FeelingsController {

    private IFeelingsService feelingsService;

    @GetMapping("/{id}")
    public ResponseEntity<FeelingsDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Feelings feelings = this.feelingsService.findById(id);
        if (feelings == null) {
            return new ResponseEntity<>(new FeelingsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(FeelingsMapper.INSTANCE.toFeelingsDTO(feelings), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<FeelingsDTO> create(
            @Valid @RequestBody FeelingsDTO feelingsDTO) {
        Feelings feelings = FeelingsMapper.INSTANCE.toFeelings(feelingsDTO);
        var feelingsBD = this.feelingsService.create(feelings);
        if (feelingsBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(FeelingsMapper.INSTANCE.toFeelingsDTO(feelingsBD));
    }

    @PutMapping
    public ResponseEntity<FeelingsDTO> update(
            @Valid @RequestBody FeelingsDTO feelingsDTO) {
        Feelings feelings = FeelingsMapper.INSTANCE.toFeelings(feelingsDTO);
        var feelingsBD = this.feelingsService.update(feelings);
        if (feelingsBD == null) {
            return new ResponseEntity<>(new FeelingsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(FeelingsMapper.INSTANCE.toFeelingsDTO(feelingsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.feelingsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setFeelingsService(IFeelingsService feelingsService) {
        this.feelingsService = feelingsService;
    }

}
