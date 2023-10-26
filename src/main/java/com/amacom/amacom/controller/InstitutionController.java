package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.TipoInstitucionDTO;
import com.amacom.amacom.mapper.InstitutionMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.TipoInstitucionMapper;
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IInstitutionService;
import com.amacom.amacom.service.interfaces.ITipoInstitucionService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

    private IInstitutionService institutionService;

    private ITipoInstitucionService tipoInstitucionService;


    @GetMapping("/consulta")
    public ResponseEntity<Page<InstitutionDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idTipoInstitucion", required = false) UUID idTipoInstitucion,
            @RequestParam(name = "query", required = false) String query) {

        var institutionPage = this.institutionService.findInstitution(idTipoInstitucion, query, ITools.getPageRequest(pageable, InstitutionMapper.getClavesToSort()));

        if (institutionPage == null || institutionPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutionPage
                .map(InstitutionMapper.INSTANCE::toInstitutionDTO), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDTO> findById(
            @PathVariable(value = "id") UUID id){
        Institution institution = this.institutionService.findById(id);
        if (institution == null) {
            return new ResponseEntity<>(new InstitutionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionMapper.INSTANCE.toInstitutionDTO(institution), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InstitutionDTO> create(
            @Valid @RequestBody InstitutionDTO institutionDTO){

        Institution institution = InstitutionMapper.INSTANCE.toInstitution(institutionDTO);

        institution.setTipoInstitucion(this.tipoInstitucionService.getEntityFromUUID(institutionDTO.getIdTipoInstitucion()));

        var institutionBD = this.institutionService.create(institution);
        if(institutionBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InstitutionMapper.INSTANCE.toInstitutionDTO(institutionBD));
    }

    @PutMapping
    public ResponseEntity<InstitutionDTO> update(
            @Valid @RequestBody InstitutionDTO institutionDTO){
        Institution institution = InstitutionMapper.INSTANCE.toInstitution(institutionDTO);

        institution.setTipoInstitucion(this.tipoInstitucionService.getEntityFromUUID(institutionDTO.getIdTipoInstitucion()));

        var institutionBD = this.institutionService.update(institution);
        if (institutionBD == null) {
            return new ResponseEntity<>(new InstitutionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionMapper.INSTANCE.toInstitutionDTO(institutionBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.institutionService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setInstitutionService(IInstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @Autowired
    public void setTipoInstitucionService(ITipoInstitucionService tipoInstitucionService) {
        this.tipoInstitucionService = tipoInstitucionService;
    }
}
