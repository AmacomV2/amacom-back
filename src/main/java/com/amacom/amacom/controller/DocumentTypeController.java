package com.amacom.amacom.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.amacom.amacom.dto.DocumentTypeDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.DocumentTypeMapper;
import com.amacom.amacom.model.DocumentType;
import com.amacom.amacom.service.interfaces.IDocumentTypeService;

@RestController
@RequestMapping("/documentType")
public class DocumentTypeController {

    private IDocumentTypeService documentTypeService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<DocumentType> documentTypeList = this.documentTypeService.getAll();
        if (documentTypeList == null || documentTypeList.isEmpty()) {
            return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(documentTypeList.stream()
                .map(DocumentTypeMapper.INSTANCE::toDocumentTypeDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        DocumentType documentType = this.documentTypeService.findById(id);
        if (documentType == null) {
            return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(DocumentTypeMapper.INSTANCE.toDocumentTypeDTO(documentType)),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = DocumentTypeMapper.INSTANCE.toDocumentType(documentTypeDTO);
        var documentTypeBD = this.documentTypeService.create(documentType);
        if (documentTypeBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(DocumentTypeMapper.INSTANCE.toDocumentTypeDTO(documentTypeBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = DocumentTypeMapper.INSTANCE.toDocumentType(documentTypeDTO);
        var documentTypeBD = this.documentTypeService.update(documentType);
        if (documentTypeBD == null) {
            return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(DocumentTypeMapper.INSTANCE.toDocumentTypeDTO(documentTypeBD)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.documentTypeService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setDocumentTypeService(IDocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
}
