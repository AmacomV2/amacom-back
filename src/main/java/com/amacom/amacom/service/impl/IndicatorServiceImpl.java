package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Indicator;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IIndicatorRepository;
import com.amacom.amacom.service.interfaces.IIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class IndicatorServiceImpl implements IIndicatorService {

    private IIndicatorRepository indicatorRepository;

    private EntityManager entityManager;


    @Override
    public Indicator getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return indicatorRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public Indicator findById(UUID id) {
        return this.indicatorRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Indicator create(Indicator indicator) {
        indicator.setId(UUID.randomUUID());
        var indicatorBD = this.indicatorRepository.save(indicator);
        this.entityManager.flush();
        this.entityManager.refresh(indicatorBD);
        return indicatorBD;
    }

    @Override
    public Indicator update(Indicator indicator) {
        var indicatorBD = this.indicatorRepository.findById(indicator.getId()).orElseThrow(DataNotFoundException::new);
        indicatorBD.setNombre(indicator.getNombre());
        return this.indicatorRepository.save(indicatorBD);
    }

    @Override
    public void deleteById(UUID id) {
        var indicatorBD = this.indicatorRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.indicatorRepository.deleteById(indicatorBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setIndicatorRepository(IIndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

}
