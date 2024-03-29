package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Gender;
import com.amacom.amacom.repository.IGenderRepository;
import com.amacom.amacom.service.interfaces.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

    private IGenderRepository genderRepository;

    private EntityManager entityManager;

    @Override
    public Gender getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return genderRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<Gender> getAll() {
        return this.genderRepository.findAll();
    }

    @Override
    public Gender findById(UUID id) {
        return this.genderRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Gender create(Gender gender) {
        this.validateCreation(gender);
        gender.setId(UUID.randomUUID());
        var genderBD = this.genderRepository.save(gender);
        this.entityManager.flush();
        this.entityManager.refresh(genderBD);
        return genderBD;
    }

    @Override
    public Gender update(Gender gender) {
        this.validateCreation(gender);
        var genderBD = this.genderRepository.findById(gender.getId()).orElseThrow(DataNotFoundException::new);
        genderBD.setName(gender.getName());
        return this.genderRepository.save(genderBD);
    }

    @Override
    public void deleteById(UUID id) {
        var genderBD = this.genderRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.genderRepository.deleteById(genderBD.getId());
    }

    private void validateCreation(Gender gender) {

        var existsSimilar = this.genderRepository.existByName(gender.getId(), gender.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setGenderRepository(IGenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
