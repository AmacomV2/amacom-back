package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IFeelingsRepository;
import com.amacom.amacom.service.interfaces.IFeelingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
        this.validarCreacion(feelings);
        feelings.setId(UUID.randomUUID());
        feelings.setFechaHoraCreacion(new Date());
        var feelingsBD = this.feelingsRepository.save(feelings);
        this.entityManager.flush();
        this.entityManager.refresh(feelingsBD);
        return feelingsBD;
    }

    @Override
    public Feelings update(Feelings feelings) {
        this.validarCreacion(feelings);
        var feelingsBD = this.feelingsRepository.findById(feelings.getId()).orElseThrow(DataNotFoundException::new);
        feelingsBD.setNombre(feelings.getNombre());
        feelingsBD.setDescripcion(feelings.getDescripcion());
        feelingsBD.setEstado(feelings.getEstado());
        feelingsBD.setFechaHoraModificacion(new Date());
        return this.feelingsRepository.save(feelingsBD);
    }

    @Override
    public void deleteById(UUID id) {
        var feelingsBD = this.feelingsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.feelingsRepository.deleteById(feelingsBD.getId());
    }

    private void validarCreacion(Feelings feelings){

        var existsSimilar = this.feelingsRepository.existsByNombre(feelings.getId(), feelings.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
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
