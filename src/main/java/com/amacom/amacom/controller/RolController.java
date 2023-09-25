package com.amacom.amacom.controller;

import com.amacom.amacom.dto.RolDTO;
import com.amacom.amacom.dto.TipoDocumentoDTO;
import com.amacom.amacom.mapper.RolMapper;
import com.amacom.amacom.mapper.TipoDocumentoMapper;
import com.amacom.amacom.model.TipoDocumento;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.service.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")
public class RolController {

    private IRolService rolService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RolDTO>> getAll(){
        List<Rol> rolList = this.rolService.getAll();
        if (rolList == null || rolList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rolList.stream()
                .map(RolMapper.INSTANCE::toRolDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Autowired
    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }
}
