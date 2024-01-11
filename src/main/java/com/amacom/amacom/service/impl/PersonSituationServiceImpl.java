package com.amacom.amacom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.amacom.amacom.model.PersonSituationHasFeelings;
import com.amacom.amacom.repository.IPersonSituationHasFeelingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.repository.IPersonSituationRepository;
import com.amacom.amacom.service.interfaces.IPersonSituationService;

@Service
public class PersonSituationServiceImpl implements IPersonSituationService {

    private IPersonSituationRepository personSituationRepository;
    @Autowired
    private IPersonSituationHasFeelingsRepository personSituationHasFeelingsRepository;

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

    public Page<PersonSituation> search(@Nullable EConsultationAlert consultationAlert,
            @Nullable EConsultationStatus consultationStatus, String query, UUID personId, Pageable pageable) {

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("createdAt").descending());

        }
        if (consultationAlert != null || consultationStatus != null) {

            return this.personSituationRepository.findPersonSituationDiagnosis(consultationAlert, consultationStatus,
                    query, personId, pageable);
        }
        return this.personSituationRepository.findPersonSituation(query, personId, pageable);
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

        //FEELINGS
        List<PersonSituationHasFeelings> deleteSituationFeeling = new ArrayList<>();
        if (personSituation.getFeelings() != null) {
            //cuales se eliminan
            /*personSituationBD.getFeelings().forEach(feel ->{
                int index = personSituation.getFeelings().ind
                if(index == -1) {
                    //se elimino este feeling
                    deleteSituationFeeling.add(feel);
                }else{
                    //ya esta agregado este feeling
                    personSituation.getFeelings().remove(index);
                }
            });*/
            personSituationBD.getFeelings().removeIf((val)->true);
            personSituationBD.getFeelings().addAll(personSituation.getFeelings());
        }
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
