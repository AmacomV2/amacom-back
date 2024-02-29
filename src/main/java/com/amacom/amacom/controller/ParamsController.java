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

import com.amacom.amacom.dto.ParamsDTO;
import com.amacom.amacom.mapper.ParamsMapper;
import com.amacom.amacom.model.Params;
import com.amacom.amacom.service.interfaces.IParamsService;

@RestController
@RequestMapping("/params")
public class ParamsController {

    private IParamsService paramsService;

    @GetMapping("/{id}")
    public ResponseEntity<ParamsDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Params params = this.paramsService.findById(id);
        if (params == null) {
            return new ResponseEntity<>(new ParamsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ParamsMapper.INSTANCE.toParamsDTO(params), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ParamsDTO> create(
            @Valid @RequestBody ParamsDTO paramsDTO) {
        Params params = ParamsMapper.INSTANCE.toParams(paramsDTO);
        var paramsBD = this.paramsService.create(params);
        if (paramsBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ParamsMapper.INSTANCE.toParamsDTO(paramsBD));
    }

    @PutMapping
    public ResponseEntity<ParamsDTO> update(
            @Valid @RequestBody ParamsDTO paramsDTO) {
        Params params = ParamsMapper.INSTANCE.toParams(paramsDTO);
        var paramsBD = this.paramsService.update(params);
        if (paramsBD == null) {
            return new ResponseEntity<>(new ParamsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ParamsMapper.INSTANCE.toParamsDTO(paramsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.paramsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setParamsService(IParamsService paramsService) {
        this.paramsService = paramsService;
    }

}
