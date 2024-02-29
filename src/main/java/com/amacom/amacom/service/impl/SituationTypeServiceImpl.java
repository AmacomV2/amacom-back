package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.SituationType;
import com.amacom.amacom.repository.ISituationTypeRepository;
import com.amacom.amacom.service.interfaces.ISituationTypeService;

@Service
public class SituationTypeServiceImpl implements ISituationTypeService {

    private ISituationTypeRepository situationTypeRepository;

    private EntityManager entityManager;

    @Override
    public SituationType getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return situationTypeRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public SituationType findById(UUID id) {
        return this.situationTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public SituationType create(SituationType situationType) {
        this.validateCreation(situationType);
        situationType.setId(UUID.randomUUID());
        var situationTypeBD = this.situationTypeRepository.save(situationType);
        this.entityManager.flush();
        this.entityManager.refresh(situationTypeBD);
        return situationTypeBD;
    }

    @Override
    public SituationType update(SituationType situationType) {
        this.validateCreation(situationType);
        var situationTypeBD = this.situationTypeRepository.findById(situationType.getId())
                .orElseThrow(DataNotFoundException::new);
        situationTypeBD.setName(situationType.getName());
        situationTypeBD.setDescription(situationType.getDescription());
        return this.situationTypeRepository.save(situationTypeBD);
    }

    @Override
    public void deleteById(UUID id) {
        var situationTypeBD = this.situationTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.situationTypeRepository.deleteById(situationTypeBD.getId());
    }

    private void validateCreation(SituationType situationType) {

        var existsSimilar = this.situationTypeRepository.existByName(situationType.getId(),
                situationType.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSituationTypeRepository(ISituationTypeRepository situationTypeRepository) {
        this.situationTypeRepository = situationTypeRepository;
    }

}
