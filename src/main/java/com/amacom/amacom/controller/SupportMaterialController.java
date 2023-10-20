package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.SupportMaterialDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.SupportMaterialMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/supportMaterial")
public class SupportMaterialController {

    private ISupportMaterialService supportMaterialService;


    @GetMapping("/{id}")
    public ResponseEntity<SupportMaterialDTO> findById(
            @PathVariable(value = "id") UUID id){
        SupportMaterial supportMaterial = this.supportMaterialService.findById(id);
        if (supportMaterial == null) {
            return new ResponseEntity<>(new SupportMaterialDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterial), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SupportMaterialDTO> create(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO){
        SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
        var supportMaterialBD = this.supportMaterialService.create(supportMaterial);
        if(supportMaterialBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD));
    }

    @PutMapping
    public ResponseEntity<SupportMaterialDTO> update(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO){
        SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
        var supportMaterialBD = this.supportMaterialService.update(supportMaterial);
        if (supportMaterialBD == null) {
            return new ResponseEntity<>(new SupportMaterialDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.supportMaterialService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSupportMaterialService(ISupportMaterialService supportMaterialService) {
        this.supportMaterialService = supportMaterialService;
    }


}
