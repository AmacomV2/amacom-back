package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.EAlarmSignType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import com.amacom.amacom.repository.IPersonSituationHasAlarmSignsRepository;
import com.amacom.amacom.service.interfaces.IPersonSituationHasAlarmSignsService;

@Service
public class PersonSituationHasAlarmSignsServiceImpl implements IPersonSituationHasAlarmSignsService {

    private IPersonSituationHasAlarmSignsRepository personSituationHasAlarmSignsRepository;

    private EntityManager entityManager;

    @Override
    public PersonSituationHasAlarmSigns getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personSituationHasAlarmSignsRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<PersonSituationHasAlarmSigns> findAlarmSign(EAlarmSignType type, String query, Pageable pageable, UUID situationId) {
        return this.personSituationHasAlarmSignsRepository.findAlarmSign(type, query, situationId, pageable);
    }

    @Override
    public PersonSituationHasAlarmSigns findById(UUID id) {
        return this.personSituationHasAlarmSignsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonSituationHasAlarmSigns create(PersonSituationHasAlarmSigns personSituationHasAlarmSigns) {
        personSituationHasAlarmSigns.setId(UUID.randomUUID());
        var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsRepository
                .save(personSituationHasAlarmSigns);
        this.entityManager.flush();
        this.entityManager.refresh(personSituationHasAlarmSignsBD);
        return personSituationHasAlarmSignsBD;
    }

    @Override
    public PersonSituationHasAlarmSigns update(PersonSituationHasAlarmSigns personSituationHasAlarmSigns) {
        var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsRepository
                .findById(personSituationHasAlarmSigns.getId()).orElseThrow(DataNotFoundException::new);
        personSituationHasAlarmSignsBD.setPersonSituation(personSituationHasAlarmSigns.getPersonSituation());
        personSituationHasAlarmSignsBD.setAlarmSign(personSituationHasAlarmSigns.getAlarmSign());
        return this.personSituationHasAlarmSignsRepository.save(personSituationHasAlarmSignsBD);
    }

    @Override
    public void deleteById(UUID id) {
        var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.personSituationHasAlarmSignsRepository.deleteById(personSituationHasAlarmSignsBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonSituationHasAlarmSignsRepository(
            IPersonSituationHasAlarmSignsRepository personSituationHasAlarmSignsRepository) {
        this.personSituationHasAlarmSignsRepository = personSituationHasAlarmSignsRepository;
    }

}
