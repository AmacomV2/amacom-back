package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.ResultHasIndicator;
import com.amacom.amacom.repository.IResultHasIndicatorRepository;
import com.amacom.amacom.service.interfaces.IResultHasIndicatorService;

@Service
public class ResultHasIndicatorServiceImpl implements IResultHasIndicatorService {

    private IResultHasIndicatorRepository resultHasIndicatorRepository;

    private EntityManager entityManager;

    @Override
    public ResultHasIndicator getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return resultHasIndicatorRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public ResultHasIndicator findById(UUID id) {
        return this.resultHasIndicatorRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public ResultHasIndicator create(ResultHasIndicator resultHasIndicator) {
        resultHasIndicator.setId(UUID.randomUUID());
        var resultHasIndicatorBD = this.resultHasIndicatorRepository.save(resultHasIndicator);
        this.entityManager.flush();
        this.entityManager.refresh(resultHasIndicatorBD);
        return resultHasIndicatorBD;
    }

    @Override
    public ResultHasIndicator update(ResultHasIndicator resultHasIndicator) {
        var resultHasIndicatorBD = this.resultHasIndicatorRepository.findById(resultHasIndicator.getId())
                .orElseThrow(DataNotFoundException::new);
        resultHasIndicatorBD.setResult(resultHasIndicator.getResult());
        resultHasIndicatorBD.setIndicator(resultHasIndicator.getIndicator());
        return this.resultHasIndicatorRepository.save(resultHasIndicatorBD);
    }

    @Override
    public void deleteById(UUID id) {
        var resultHasIndicatorBD = this.resultHasIndicatorRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.resultHasIndicatorRepository.deleteById(resultHasIndicatorBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setResultHasIndicatorRepository(IResultHasIndicatorRepository resultHasIndicatorRepository) {
        this.resultHasIndicatorRepository = resultHasIndicatorRepository;
    }

}
