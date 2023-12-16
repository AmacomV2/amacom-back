package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.repository.IFeelingsRepository;
import com.amacom.amacom.service.interfaces.IFeelingsService;

@Service
public class FeelingsServiceImpl implements IFeelingsService {

    private IFeelingsRepository feelingsRepository;

    private EntityManager entityManager;

    @Override
    public Feelings getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return feelingsRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Feelings findById(UUID id) {
        return this.feelingsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Feelings create(Feelings feelings) {
        this.validateCreation(feelings);
        feelings.setId(UUID.randomUUID());
        var feelingsBD = this.feelingsRepository.save(feelings);
        this.entityManager.flush();
        this.entityManager.refresh(feelingsBD);
        return feelingsBD;
    }

    @Override
    public Feelings update(Feelings feelings) {
        this.validateCreation(feelings);
        var feelingsBD = this.feelingsRepository.findById(feelings.getId()).orElseThrow(DataNotFoundException::new);
        feelingsBD.setName(feelings.getName());
        feelingsBD.setDescription(feelings.getDescription());
        feelingsBD.setStatus(feelings.getStatus());
        return this.feelingsRepository.save(feelingsBD);
    }

    @Override
    public void deleteById(UUID id) {
        var feelingsBD = this.feelingsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.feelingsRepository.deleteById(feelingsBD.getId());
    }

    private void validateCreation(Feelings feelings) {

        var existsSimilar = this.feelingsRepository.existByName(feelings.getId(), feelings.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setFeelingsRepository(IFeelingsRepository feelingsRepository) {
        this.feelingsRepository = feelingsRepository;
    }

}
