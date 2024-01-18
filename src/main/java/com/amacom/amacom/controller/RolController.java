package com.amacom.amacom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.RolMapper;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IRolService;

@RestController
@RequestMapping("/rol")
public class RolController {

    private IRolService rolService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<Rol> rolList = this.rolService.getAll();
        if (rolList == null || rolList.isEmpty()) {
            return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(rolList.stream()
                .map(RolMapper.INSTANCE::toRolDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/getCurrentRol")
    @Transactional
    public ResponseEntity<ResponseDTO> getOne() {
        try {
            var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = User.class.cast(authData);

            return new ResponseEntity<>(new SuccessDTO(user.getRol()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }
}
