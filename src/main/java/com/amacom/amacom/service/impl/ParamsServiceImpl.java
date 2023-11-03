package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.Params;
import com.amacom.amacom.repository.IParamsRepository;
import com.amacom.amacom.service.interfaces.IParamsService;

@Service
public class ParamsServiceImpl implements IParamsService {

    private IParamsRepository paramsRepository;

    private EntityManager entityManager;

    @Override
    public Params getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return paramsRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Params findById(UUID id) {
        return this.paramsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Params create(Params params) {
        params.setId(UUID.randomUUID());
        params.setCreatedAt(new Date());
        var paramsBD = this.paramsRepository.save(params);
        this.entityManager.flush();
        this.entityManager.refresh(paramsBD);
        return paramsBD;
    }

    @Override
    public Params update(Params params) {
        var paramsBD = this.paramsRepository.findById(params.getId()).orElseThrow(DataNotFoundException::new);
        paramsBD.setUpdatedAt(new Date());
        return this.paramsRepository.save(paramsBD);
    }

    @Override
    public void deleteById(UUID id) {
        var paramsBD = this.paramsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.paramsRepository.deleteById(paramsBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setParamsRepository(IParamsRepository paramsRepository) {
        this.paramsRepository = paramsRepository;
    }

}
