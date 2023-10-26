package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InstitutionDTO;
import com.amacom.amacom.dto.InstitutionServiceDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.InstitutionMapper;
import com.amacom.amacom.mapper.InstitutionServiceMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IInstitutionService;
import com.amacom.amacom.service.interfaces.IInstitutionServiceService;
import com.amacom.amacom.service.interfaces.IServicesService;
import com.amacom.amacom.service.interfaces.IUsuarioService;
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
@RequestMapping("/institutionService")
public class InstitutionServiceController {

    private IInstitutionServiceService institutionServiceService;

    private IUsuarioService usuarioService;

    private IServicesService servicesService;

    private IInstitutionService institutionService;



    @GetMapping("/consulta")
    public ResponseEntity<Page<InstitutionServiceDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idInstitution", required = false) UUID idInstitution,
            @RequestParam(name = "idService", required = false) UUID idService,
            @RequestParam(name = "query", required = false) String query) {

        var institutionServicePage = this.institutionServiceService.findInstitutionService(idInstitution, idService, query, ITools.getPageRequest(pageable, InstitutionServiceMapper.getClavesToSort()));

        if (institutionServicePage == null || institutionServicePage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutionServicePage
                .map(InstitutionServiceMapper.INSTANCE::toInstitutionServiceDTO), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InstitutionServiceDTO> findById(
            @PathVariable(value = "id") UUID id){
        InstitutionService institutionService = this.institutionServiceService.findById(id);
        if (institutionService == null) {
            return new ResponseEntity<>(new InstitutionServiceDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionServiceMapper.INSTANCE.toInstitutionServiceDTO(institutionService), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InstitutionServiceDTO> create(
            @Valid @RequestBody InstitutionServiceDTO institutionServiceDTO,
            @RequestHeader("idUsuario") UUID idUsuario){

        InstitutionService institutionService = InstitutionServiceMapper.INSTANCE.toInstitutionService(institutionServiceDTO);

        institutionService.setUsuario(this.usuarioService.getEntityFromUUID(idUsuario));
        institutionService.setServices(this.servicesService.getEntityFromUUID(institutionServiceDTO.getIdServices()));
        institutionService.setInstitution(this.institutionService.getEntityFromUUID(institutionServiceDTO.getIdInstitution()));

        var institutionServiceBD = this.institutionServiceService.create(institutionService);
        if(institutionServiceBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InstitutionServiceMapper.INSTANCE.toInstitutionServiceDTO(institutionServiceBD));
    }

    @PutMapping
    public ResponseEntity<InstitutionServiceDTO> update(
            @Valid @RequestBody InstitutionServiceDTO institutionServiceDTO){
        InstitutionService institutionService = InstitutionServiceMapper.INSTANCE.toInstitutionService(institutionServiceDTO);

        institutionService.setServices(this.servicesService.getEntityFromUUID(institutionServiceDTO.getIdServices()));
        institutionService.setInstitution(this.institutionService.getEntityFromUUID(institutionServiceDTO.getIdInstitution()));

        var institutionServiceBD = this.institutionServiceService.update(institutionService);
        if (institutionServiceBD == null) {
            return new ResponseEntity<>(new InstitutionServiceDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionServiceMapper.INSTANCE.toInstitutionServiceDTO(institutionServiceBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.institutionServiceService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setInstitutionService(IInstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setInstitutionServiceService(IInstitutionServiceService institutionServiceService) {
        this.institutionServiceService = institutionServiceService;
    }

    @Autowired
    public void setServicesService(IServicesService servicesService) {
        this.servicesService = servicesService;
    }


}
