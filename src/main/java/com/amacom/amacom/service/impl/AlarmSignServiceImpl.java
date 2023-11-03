package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.repository.IAlarmSignRepository;
import com.amacom.amacom.service.interfaces.IAlarmSignService;

@Service
public class AlarmSignServiceImpl implements IAlarmSignService {

    private IAlarmSignRepository alarmSignRepository;

    private EntityManager entityManager;

    @Override
    public AlarmSign getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return alarmSignRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public AlarmSign findById(UUID id) {
        return this.alarmSignRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public AlarmSign create(AlarmSign alarmSign) {
        this.validateCreation(alarmSign);
        alarmSign.setId(UUID.randomUUID());
        alarmSign.setCreatedAt(new Date());
        var alarmSignBD = this.alarmSignRepository.save(alarmSign);
        this.entityManager.flush();
        this.entityManager.refresh(alarmSignBD);
        return alarmSignBD;
    }

    @Override
    public AlarmSign update(AlarmSign alarmSign) {
        this.validateCreation(alarmSign);
        var alarmSignBD = this.alarmSignRepository.findById(alarmSign.getId()).orElseThrow(DataNotFoundException::new);
        alarmSignBD.setName(alarmSign.getName());
        alarmSignBD.setDescriptionType(alarmSign.getDescriptionType());
        alarmSignBD.setImageUrl(alarmSign.getImageUrl());
        alarmSignBD.setStatus(alarmSign.getStatus());
        alarmSignBD.setType(alarmSign.getType());
        alarmSignBD.setUpdatedAt(new Date());
        return this.alarmSignRepository.save(alarmSignBD);
    }

    @Override
    public void deleteById(UUID id) {
        var alarmSignBD = this.alarmSignRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.alarmSignRepository.deleteById(alarmSignBD.getId());
    }

    private void validateCreation(AlarmSign alarmSign) {

        var existsSimilar = this.alarmSignRepository.existsByNombre(alarmSign.getId(), alarmSign.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setAlarmSignRepository(IAlarmSignRepository alarmSignRepository) {
        this.alarmSignRepository = alarmSignRepository;
    }
}
