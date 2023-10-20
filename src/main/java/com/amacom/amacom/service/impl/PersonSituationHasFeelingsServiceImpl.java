package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import com.amacom.amacom.repository.IPersonSituationHasFeelingsRepository;
import com.amacom.amacom.service.interfaces.IPersonSituationHasFeelingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class PersonSituationHasFeelingsServiceImpl implements IPersonSituationHasFeelingsService {

    private IPersonSituationHasFeelingsRepository personSituationHasFeelingsRepository;

    private EntityManager entityManager;

    @Override
    public PersonSituationHasFeelings getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personSituationHasFeelingsRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public PersonSituationHasFeelings findById(UUID id) {
        return this.personSituationHasFeelingsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonSituationHasFeelings create(PersonSituationHasFeelings personSituationHasFeelings) {
        personSituationHasFeelings.setId(UUID.randomUUID());
        var personSituationHasFeelingsBD = this.personSituationHasFeelingsRepository.save(personSituationHasFeelings);
        this.entityManager.flush();
        this.entityManager.refresh(personSituationHasFeelingsBD);
        return personSituationHasFeelingsBD;
    }

    @Override
    public PersonSituationHasFeelings update(PersonSituationHasFeelings personSituationHasFeelings) {
        var personSituationHasFeelingsBD = this.personSituationHasFeelingsRepository.findById(personSituationHasFeelings.getId()).orElseThrow(DataNotFoundException::new);
        personSituationHasFeelingsBD.setPersonSituation(personSituationHasFeelings.getPersonSituation());
        personSituationHasFeelingsBD.setFeelings(personSituationHasFeelings.getFeelings());
        personSituationHasFeelingsBD.setPriority(personSituationHasFeelings.getPriority());
        return this.personSituationHasFeelingsRepository.save(personSituationHasFeelingsBD);
    }

    @Override
    public void deleteById(UUID id) {
        var personSituationHasFeelingsBD = this.personSituationHasFeelingsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personSituationHasFeelingsRepository.deleteById(personSituationHasFeelingsBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonSituationHasFeelingsRepository(IPersonSituationHasFeelingsRepository personSituationHasFeelingsRepository) {
        this.personSituationHasFeelingsRepository = personSituationHasFeelingsRepository;
    }

}
