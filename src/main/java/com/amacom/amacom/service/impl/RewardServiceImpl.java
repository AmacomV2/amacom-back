package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Reward;
import com.amacom.amacom.repository.IRewardRepository;
import com.amacom.amacom.service.interfaces.IRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class RewardServiceImpl implements IRewardService {

    private IRewardRepository rewardRepository;

    private EntityManager entityManager;


    @Override
    public Reward getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return rewardRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public Reward findById(UUID id) {
        return this.rewardRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Reward create(Reward reward) {
        this.validarCreacion(reward);
        reward.setId(UUID.randomUUID());
        reward.setFechaHoraCreacion(new Date());
        var rewardBD = this.rewardRepository.save(reward);
        this.entityManager.flush();
        this.entityManager.refresh(rewardBD);
        return rewardBD;
    }

    @Override
    public Reward update(Reward reward) {
        this.validarCreacion(reward);
        var rewardBD = this.rewardRepository.findById(reward.getId()).orElseThrow(DataNotFoundException::new);
        rewardBD.setSubject(reward.getSubject());
        rewardBD.setNombre(reward.getNombre());
        rewardBD.setDescripcion(reward.getDescripcion());
        rewardBD.setPuntajeMinimo(reward.getPuntajeMinimo());
        rewardBD.setPuntajeMaximo(reward.getPuntajeMaximo());
        rewardBD.setNivel(reward.getNivel());
        rewardBD.setFechaHoraModificacion(new Date());
        return this.rewardRepository.save(rewardBD);
    }

    @Override
    public void deleteById(UUID id) {
        var rewardBD = this.rewardRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.rewardRepository.deleteById(rewardBD.getId());
    }


    private void validarCreacion(Reward reward){

        var existsSimilar = this.rewardRepository.existsByNombre(reward.getId(), reward.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setRewardRepository(IRewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }
}
