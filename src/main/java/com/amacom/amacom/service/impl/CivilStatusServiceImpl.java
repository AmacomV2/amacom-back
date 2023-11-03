package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.CivilStatus;
import com.amacom.amacom.repository.ICivilStatusRepository;
import com.amacom.amacom.service.interfaces.ICivilStatusService;

@Service
public class CivilStatusServiceImpl implements ICivilStatusService {

    private ICivilStatusRepository civilStatusRepository;

    @Override
    public CivilStatus getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return civilStatusRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<CivilStatus> getAll() {
        return this.civilStatusRepository.findAll();
    }

    @Autowired
    public void setCivilStatusRepository(ICivilStatusRepository civilStatusRepository) {
        this.civilStatusRepository = civilStatusRepository;
    }
}
