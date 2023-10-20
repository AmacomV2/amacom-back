package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IAchievementRepository;
import com.amacom.amacom.service.interfaces.IAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
    public Achievement findById(UUID id) {
        return this.achievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Achievement create(Achievement achievement) {
        this.validarCreacion(achievement);
        achievement.setId(UUID.randomUUID());
        achievement.setFechaHoraCreacion(new Date());
        var achievementBD = this.achievementRepository.save(achievement);
        this.entityManager.flush();
        this.entityManager.refresh(achievementBD);
        return achievementBD;
    }

    @Override
    public Achievement update(Achievement achievement) {
        this.validarCreacion(achievement);
        var achievementBD = this.achievementRepository.findById(achievement.getId()).orElseThrow(DataNotFoundException::new);
        achievementBD.setSubject(achievement.getSubject());
        achievementBD.setNombre(achievement.getNombre());
        achievementBD.setFechaHoraModificacion(new Date());
        return this.achievementRepository.save(achievementBD);
    }

    @Override
    public void deleteById(UUID id) {
        var achievementBD = this.achievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.achievementRepository.deleteById(achievementBD.getId());
    }

    private void validarCreacion(Achievement achievement){

        var existsSimilar = this.achievementRepository.existsByNombre(achievement.getId(), achievement.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
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
