package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.repository.IAchievementRepository;
import com.amacom.amacom.service.interfaces.IAchievementService;

@Service
public class AchievementServiceImpl implements IAchievementService {

    private IAchievementRepository achievementRepository;

    private EntityManager entityManager;

    @Override
    public Achievement getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return achievementRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<Achievement> findAchievement(UUID subjectId, String query, Pageable pageable) {
        Page<Achievement> achievementPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            achievementPage = this.achievementRepository.findAchievement(subjectId, query, pageableDefault);
        } else {
            achievementPage = this.achievementRepository.findAchievement(subjectId, query, pageable);
        }
        return achievementPage;
    }

    @Override
    public Achievement findById(UUID id) {
        return this.achievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Achievement create(Achievement achievement) {
        this.validateCreation(achievement);
        achievement.setId(UUID.randomUUID());
        achievement.setCreatedAt(new Date());
        var achievementBD = this.achievementRepository.save(achievement);
        this.entityManager.flush();
        this.entityManager.refresh(achievementBD);
        return achievementBD;
    }

    @Override
    public Achievement update(Achievement achievement) {
        this.validateCreation(achievement);
        var achievementBD = this.achievementRepository.findById(achievement.getId())
                .orElseThrow(DataNotFoundException::new);
        achievementBD.setSubject(achievement.getSubject());
        achievementBD.setName(achievement.getName());
        achievementBD.setUpdatedAt(new Date());
        return this.achievementRepository.save(achievementBD);
    }

    @Override
    public void deleteById(UUID id) {
        var achievementBD = this.achievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.achievementRepository.deleteById(achievementBD.getId());
    }

    private void validateCreation(Achievement achievement) {

        var existsSimilar = this.achievementRepository.existsByNombre(achievement.getId(), achievement.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setAchievementRepository(IAchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }
}
