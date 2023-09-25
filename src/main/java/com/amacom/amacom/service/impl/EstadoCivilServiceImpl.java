package com.amacom.amacom.service.impl;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.repository.IEstadoCivilRepository;
import com.amacom.amacom.service.interfaces.IEstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilServiceImpl implements IEstadoCivilService {

    private IEstadoCivilRepository estadoCivilRepository;

    @Override
    public List<EstadoCivil> getAll() {
        return this.estadoCivilRepository.findAll();
    }

    @Autowired
    public void setEstadoCivilRepository(IEstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }
}
