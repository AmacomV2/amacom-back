package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.ServicesDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.ServicesMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Services;
import com.amacom.amacom.service.interfaces.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private IServicesService servicesService;

    @GetMapping("/{id}")
    public ResponseEntity<ServicesDTO> findById(
            @PathVariable(value = "id") UUID id){
        Services services = this.servicesService.findById(id);
        if (services == null) {
            return new ResponseEntity<>(new ServicesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ServicesMapper.INSTANCE.toServicesDTO(services), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ServicesDTO> create(
            @Valid @RequestBody ServicesDTO servicesDTO){
        Services services = ServicesMapper.INSTANCE.toServices(servicesDTO);
        var servicesBD = this.servicesService.create(services);
        if(servicesBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ServicesMapper.INSTANCE.toServicesDTO(servicesBD));
    }

    @PutMapping
    public ResponseEntity<ServicesDTO> update(
            @Valid @RequestBody ServicesDTO servicesDTO){
        Services services = ServicesMapper.INSTANCE.toServices(servicesDTO);
        var servicesBD = this.servicesService.update(services);
        if (servicesBD == null) {
            return new ResponseEntity<>(new ServicesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ServicesMapper.INSTANCE.toServicesDTO(servicesBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.servicesService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setServicesService(IServicesService servicesService) {
        this.servicesService = servicesService;
    }


}
