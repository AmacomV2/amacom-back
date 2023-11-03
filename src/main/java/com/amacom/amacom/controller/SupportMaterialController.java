package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.amacom.amacom.dto.SupportMaterialDTO;
import com.amacom.amacom.mapper.SupportMaterialMapper;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/supportMaterial")
public class SupportMaterialController {

    private ISupportMaterialService supportMaterialService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<SupportMaterialDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "subjectId", required = false) UUID subjectId,
            @RequestParam(name = "query", required = false) String query) {

        var supportMaterialPage = this.supportMaterialService.findSupportMaterial(subjectId, query,
                ITools.getPageRequest(pageable, SupportMaterialMapper.getClavesToSort()));

        if (supportMaterialPage == null || supportMaterialPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supportMaterialPage
                .map(SupportMaterialMapper.INSTANCE::toSupportMaterialDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportMaterialDTO> findById(
            @PathVariable(value = "id") UUID id) {
        SupportMaterial supportMaterial = this.supportMaterialService.findById(id);
        if (supportMaterial == null) {
            return new ResponseEntity<>(new SupportMaterialDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterial),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SupportMaterialDTO> create(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO) {
        SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
        var supportMaterialBD = this.supportMaterialService.create(supportMaterial);
        if (supportMaterialBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD));
    }

    @PutMapping
    public ResponseEntity<SupportMaterialDTO> update(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO) {
        SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
        var supportMaterialBD = this.supportMaterialService.update(supportMaterial);
        if (supportMaterialBD == null) {
            return new ResponseEntity<>(new SupportMaterialDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.supportMaterialService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSupportMaterialService(ISupportMaterialService supportMaterialService) {
        this.supportMaterialService = supportMaterialService;
    }

}
