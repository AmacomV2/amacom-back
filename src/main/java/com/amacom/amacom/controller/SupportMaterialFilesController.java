package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.SupportMaterialFilesDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.SupportMaterialFilesMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.SupportMaterialFiles;
import com.amacom.amacom.service.interfaces.ISupportMaterialFilesService;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/supportMaterialFiles")
public class SupportMaterialFilesController {

    private ISupportMaterialFilesService supportMaterialFilesService;

    private ISupportMaterialService supportMaterialService;


    @GetMapping("/{id}")
    public ResponseEntity<SupportMaterialFilesDTO> findById(
            @PathVariable(value = "id") UUID id){
        SupportMaterialFiles supportMaterialFiles = this.supportMaterialFilesService.findById(id);
        if (supportMaterialFiles == null) {
            return new ResponseEntity<>(new SupportMaterialFilesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialFilesMapper.INSTANCE.toSupportMaterialFilesDTO(supportMaterialFiles), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SupportMaterialFilesDTO> create(
            @Valid @RequestBody SupportMaterialFilesDTO supportMaterialFilesDTO){

        SupportMaterialFiles supportMaterialFiles = SupportMaterialFilesMapper.INSTANCE.toSupportMaterialFiles(supportMaterialFilesDTO);

        supportMaterialFiles.setSupportMaterial(this.supportMaterialService.getEntityFromUUID(supportMaterialFilesDTO.getIdSupportMaterial()));

        var supportMaterialFilesBD = this.supportMaterialFilesService.create(supportMaterialFiles);
        if(supportMaterialFilesBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SupportMaterialFilesMapper.INSTANCE.toSupportMaterialFilesDTO(supportMaterialFilesBD));
    }

    @PutMapping
    public ResponseEntity<SupportMaterialFilesDTO> update(
            @Valid @RequestBody SupportMaterialFilesDTO supportMaterialFilesDTO){
        SupportMaterialFiles supportMaterialFiles = SupportMaterialFilesMapper.INSTANCE.toSupportMaterialFiles(supportMaterialFilesDTO);

        supportMaterialFiles.setSupportMaterial(this.supportMaterialService.getEntityFromUUID(supportMaterialFilesDTO.getIdSupportMaterial()));

        var supportMaterialFilesBD = this.supportMaterialFilesService.update(supportMaterialFiles);
        if (supportMaterialFilesBD == null) {
            return new ResponseEntity<>(new SupportMaterialFilesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialFilesMapper.INSTANCE.toSupportMaterialFilesDTO(supportMaterialFilesBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.supportMaterialFilesService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSupportMaterialFilesService(ISupportMaterialFilesService supportMaterialFilesService) {
        this.supportMaterialFilesService = supportMaterialFilesService;
    }

    @Autowired
    public void setSupportMaterialService(ISupportMaterialService supportMaterialService) {
        this.supportMaterialService = supportMaterialService;
    }


}
