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

import com.amacom.amacom.dto.InstitutionTypeDTO;
import com.amacom.amacom.mapper.InstitutionTypeMapper;
import com.amacom.amacom.model.InstitutionType;
import com.amacom.amacom.service.interfaces.IInstitutionTypeService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/typeInstitution")
public class InstitutionTypeController {

    private IInstitutionTypeService typeInstitutionService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<InstitutionTypeDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {

        var typeInstitutionPage = this.typeInstitutionService.findInstitutionType(query,
                ITools.getPageRequest(pageable, InstitutionTypeMapper.getSortKeys()));

        if (typeInstitutionPage == null || typeInstitutionPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeInstitutionPage
                .map(InstitutionTypeMapper.INSTANCE::toInstitutionTypeDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionTypeDTO> findById(
            @PathVariable(value = "id") UUID id) {
        InstitutionType typeInstitution = this.typeInstitutionService.findById(id);
        if (typeInstitution == null) {
            return new ResponseEntity<>(new InstitutionTypeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionTypeMapper.INSTANCE.toInstitutionTypeDTO(typeInstitution),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InstitutionTypeDTO> create(
            @Valid @RequestBody InstitutionTypeDTO typeInstitutionDTO) {
        InstitutionType typeInstitution = InstitutionTypeMapper.INSTANCE.toInstitutionType(typeInstitutionDTO);
        var typeInstitutionBD = this.typeInstitutionService.create(typeInstitution);
        if (typeInstitutionBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InstitutionTypeMapper.INSTANCE.toInstitutionTypeDTO(typeInstitutionBD));
    }

    @PutMapping
    public ResponseEntity<InstitutionTypeDTO> update(
            @Valid @RequestBody InstitutionTypeDTO typeInstitutionDTO) {
        InstitutionType typeInstitution = InstitutionTypeMapper.INSTANCE.toInstitutionType(typeInstitutionDTO);
        var typeInstitutionBD = this.typeInstitutionService.update(typeInstitution);
        if (typeInstitutionBD == null) {
            return new ResponseEntity<>(new InstitutionTypeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionTypeMapper.INSTANCE.toInstitutionTypeDTO(typeInstitutionBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.typeInstitutionService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setInstitutionTypeService(IInstitutionTypeService typeInstitutionService) {
        this.typeInstitutionService = typeInstitutionService;
    }

}
