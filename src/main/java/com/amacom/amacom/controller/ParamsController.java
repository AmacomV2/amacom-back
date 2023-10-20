package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.ParamsDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.ParamsMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Params;
import com.amacom.amacom.service.interfaces.IParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/params")
public class ParamsController {

    private IParamsService paramsService;


    @GetMapping("/{id}")
    public ResponseEntity<ParamsDTO> findById(
            @PathVariable(value = "id") UUID id){
        Params params = this.paramsService.findById(id);
        if (params == null) {
            return new ResponseEntity<>(new ParamsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ParamsMapper.INSTANCE.toParamsDTO(params), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ParamsDTO> create(
            @Valid @RequestBody ParamsDTO paramsDTO){
        Params params = ParamsMapper.INSTANCE.toParams(paramsDTO);
        var paramsBD = this.paramsService.create(params);
        if(paramsBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ParamsMapper.INSTANCE.toParamsDTO(paramsBD));
    }

    @PutMapping
    public ResponseEntity<ParamsDTO> update(
            @Valid @RequestBody ParamsDTO paramsDTO){
        Params params = ParamsMapper.INSTANCE.toParams(paramsDTO);
        var paramsBD = this.paramsService.update(params);
        if (paramsBD == null) {
            return new ResponseEntity<>(new ParamsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ParamsMapper.INSTANCE.toParamsDTO(paramsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.paramsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
    @Autowired
    public void setParamsService(IParamsService paramsService) {
        this.paramsService = paramsService;
    }

}
