package com.amacom.amacom.service.impl;

import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.repository.IRolRepository;
import com.amacom.amacom.service.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    private IRolRepository rolRepository;

    @Override
    public List<Rol> getAll() {
        return this.rolRepository.findAll();
    }

    @Autowired
    public void setRolRepository(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
}
