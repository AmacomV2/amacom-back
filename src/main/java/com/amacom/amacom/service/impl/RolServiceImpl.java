package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.auth.Rol;
import com.amacom.amacom.repository.IRolRepository;
import com.amacom.amacom.service.interfaces.IRolService;

@Service
public class RolServiceImpl implements IRolService {

    private IRolRepository rolRepository;

    @Override
    public Rol getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return rolRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<Rol> getAll() {
        return this.rolRepository.findAll();
    }

    @Autowired
    public void setRolRepository(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
}
