package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.repository.IPersonSituationRepository;
import com.amacom.amacom.service.interfaces.IPersonSituationService;

@Service
public class PersonSituationServiceImpl implements IPersonSituationService {

    private IPersonSituationRepository personSituationRepository;

    private EntityManager entityManager;

    @Override
    public PersonSituation getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return personSituationRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public PersonSituation findById(UUID id) {
        return this.personSituationRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonSituation create(PersonSituation personSituation) {
        personSituation.setId(UUID.randomUUID());
        var personSituationBD = this.personSituationRepository.save(personSituation);
        this.entityManager.flush();
        this.entityManager.refresh(personSituationBD);
        return personSituationBD;
    }

    @Override
    public PersonSituation update(PersonSituation personSituation) {
        var personSituationBD = this.personSituationRepository.findById(personSituation.getId())
                .orElseThrow(DataNotFoundException::new);
        personSituationBD.setPerson(personSituation.getPerson());
        personSituationBD.setSubject(personSituation.getSubject());
        personSituationBD.setDescription(personSituation.getDescription());
        personSituationBD.setFirstThought(personSituation.getFirstThought());
        personSituationBD.setBehavior(personSituation.getBehavior());
        personSituationBD.setAffectationDegree(personSituation.getAffectationDegree());
        personSituationBD.setNursingAssessment(personSituation.getNursingAssessment());
        return this.personSituationRepository.save(personSituationBD);
    }

    @Override
    public void deleteById(UUID id) {
        var personSituationBD = this.personSituationRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.personSituationRepository.deleteById(personSituationBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPersonSituationRepository(IPersonSituationRepository personSituationRepository) {
        this.personSituationRepository = personSituationRepository;
    }

}
