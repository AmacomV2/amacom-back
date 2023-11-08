package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.Result;
import com.amacom.amacom.repository.IResultRepository;
import com.amacom.amacom.service.interfaces.IResultService;

@Service
public class ResultServiceImpl implements IResultService {

    private IResultRepository resultRepository;

    private EntityManager entityManager;

    @Override
    public Result getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return resultRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Result findById(UUID id) {
        return this.resultRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Result create(Result result) {
        result.setId(UUID.randomUUID());
        result.setCreatedAt(new Date());
        var resultBD = this.resultRepository.save(result);
        this.entityManager.flush();
        this.entityManager.refresh(resultBD);
        return resultBD;
    }

    @Override
    public Result update(Result result) {
        var resultBD = this.resultRepository.findById(result.getId()).orElseThrow(DataNotFoundException::new);
        resultBD.setDiagnosis(result.getDiagnosis());
        resultBD.setUpdatedAt(new Date());
        return this.resultRepository.save(resultBD);
    }

    @Override
    public void deleteById(UUID id) {
        var resultBD = this.resultRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.resultRepository.deleteById(resultBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setResultRepository(IResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

}