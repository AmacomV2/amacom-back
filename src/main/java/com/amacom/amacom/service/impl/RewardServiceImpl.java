package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Reward;
import com.amacom.amacom.repository.IRewardRepository;
import com.amacom.amacom.service.interfaces.IRewardService;

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
        this.validateCreation(reward);
        reward.setId(UUID.randomUUID());
        reward.setCreatedAt(new Date());
        var rewardBD = this.rewardRepository.save(reward);
        this.entityManager.flush();
        this.entityManager.refresh(rewardBD);
        return rewardBD;
    }

    @Override
    public Reward update(Reward reward) {
        this.validateCreation(reward);
        var rewardBD = this.rewardRepository.findById(reward.getId()).orElseThrow(DataNotFoundException::new);
        rewardBD.setSubject(reward.getSubject());
        rewardBD.setName(reward.getName());
        rewardBD.setDescription(reward.getDescription());
        rewardBD.setMinScore(reward.getMinScore());
        rewardBD.setMaxScore(reward.getMaxScore());
        rewardBD.setLevel(reward.getLevel());
        rewardBD.setUpdatedAt(new Date());
        return this.rewardRepository.save(rewardBD);
    }

    @Override
    public void deleteById(UUID id) {
        var rewardBD = this.rewardRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.rewardRepository.deleteById(rewardBD.getId());
    }

    private void validateCreation(Reward reward) {

        var existsSimilar = this.rewardRepository.existsByNombre(reward.getId(), reward.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
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
