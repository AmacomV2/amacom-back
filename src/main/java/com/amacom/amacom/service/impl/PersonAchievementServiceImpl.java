package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.PersonAchievement;
import com.amacom.amacom.repository.IPersonAchievementRepository;
import com.amacom.amacom.service.interfaces.IPersonAchievementService;

@Service
public class PersonAchievementServiceImpl implements IPersonAchievementService {

    private IPersonAchievementRepository personAchievementRepository;

    private EntityManager entityManager;

    @Override
    public PersonAchievement getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personAchievementRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<PersonAchievement> getAll(UUID personId) {
        return this.personAchievementRepository.findAllByPersonId(personId);
    }

    @Override
    public PersonAchievement findById(UUID id) {
        return this.personAchievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonAchievement create(PersonAchievement personAchievement) {
        personAchievement.setId(UUID.randomUUID());
        var personAchievementBD = this.personAchievementRepository.save(personAchievement);
        this.entityManager.flush();
        this.entityManager.refresh(personAchievementBD);
        return personAchievementBD;
    }

    @Override
    public PersonAchievement update(PersonAchievement personAchievement) {
        var personAchievementBD = this.personAchievementRepository.findById(personAchievement.getId())
                .orElseThrow(DataNotFoundException::new);
        personAchievementBD.setScore(personAchievement.getScore());
        personAchievementBD.setPerson(personAchievement.getPerson());
        personAchievementBD.setAchievement(personAchievement.getAchievement());
        return this.personAchievementRepository.save(personAchievementBD);
    }

    @Override
    public void deleteById(UUID id) {
        var personAchievementBD = this.personAchievementRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personAchievementRepository.deleteById(personAchievementBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonAchievementRepository(IPersonAchievementRepository personAchievementRepository) {
        this.personAchievementRepository = personAchievementRepository;
    }

}
