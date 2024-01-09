package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.SupportMaterialMapper;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/supportMaterial")
public class SupportMaterialController {

    private ISupportMaterialService supportMaterialService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "subjectId", required = false) UUID subjectId,
            @RequestParam(name = "query", required = false) String query) {

        try {
            var supportMaterialPage = this.supportMaterialService.findSupportMaterial(subjectId, query,
                    ITools.getPageRequest(pageable, SupportMaterialMapper.getSortKeys()));

            if (supportMaterialPage == null || supportMaterialPage.getContent().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(new SuccessDTO(supportMaterialPage
                    .map(SupportMaterialMapper.INSTANCE::toSupportMaterialDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        try {
            SupportMaterial supportMaterial = this.supportMaterialService.findById(id);

            return ResponseEntity
                    .ok(new SuccessDTO(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterial)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO) {
        try {

            SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
            var supportMaterialBD = this.supportMaterialService.create(supportMaterial);
            return ResponseEntity
                    .ok(new SuccessDTO(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);

        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody SupportMaterialDTO supportMaterialDTO) {
        try {

            SupportMaterial supportMaterial = SupportMaterialMapper.INSTANCE.toSupportMaterial(supportMaterialDTO);
            var supportMaterialBD = this.supportMaterialService.update(supportMaterial);

            return ResponseEntity
                    .ok(new SuccessDTO(SupportMaterialMapper.INSTANCE.toSupportMaterialDTO(supportMaterialBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        try {
            this.supportMaterialService.deleteById(id);
            return ResponseEntity.ok(new SuccessDTO());

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);

        }
    }

    @Autowired
    public void setSupportMaterialService(ISupportMaterialService supportMaterialService) {
        this.supportMaterialService = supportMaterialService;
    }

}
