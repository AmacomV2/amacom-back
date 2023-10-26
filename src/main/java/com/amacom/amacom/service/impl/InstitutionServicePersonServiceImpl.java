package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IInstitutionServicePersonRepository;
import com.amacom.amacom.service.interfaces.IInstitutionServicePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class InstitutionServicePersonServiceImpl implements IInstitutionServicePersonService {

    private IInstitutionServicePersonRepository institutionServicePersonRepository;

    private EntityManager entityManager;


    @Override
    public InstitutionServicePerson getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return institutionServicePersonRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<InstitutionServicePerson> findInstitutionServicePerson(UUID idInstitutionService, UUID idPersona, String query, Pageable pageable) {
        Page<InstitutionServicePerson> institutionServicePersonPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("institutionService.institution.nombre").ascending().and(Sort.by("institutionService.institution.fechaHoraCreacion").descending()));
            institutionServicePersonPage = this.institutionServicePersonRepository.findInstitutionServicePerson(idInstitutionService, idPersona, query, pageableDefault);
        }
        else{
            institutionServicePersonPage = this.institutionServicePersonRepository.findInstitutionServicePerson(idInstitutionService, idPersona, query, pageable);
        }
        return institutionServicePersonPage;
    }

    @Override
    public InstitutionServicePerson findById(UUID id) {
        return this.institutionServicePersonRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public InstitutionServicePerson create(InstitutionServicePerson institutionServicePerson) {
        institutionServicePerson.setId(UUID.randomUUID());
        var institutionServicePersonBD = this.institutionServicePersonRepository.save(institutionServicePerson);
        this.entityManager.flush();
        this.entityManager.refresh(institutionServicePersonBD);
        return institutionServicePersonBD;
    }

    @Override
    public InstitutionServicePerson update(InstitutionServicePerson institutionServicePerson) {
        var institutionServicePersonBD = this.institutionServicePersonRepository.findById(institutionServicePerson.getId()).orElseThrow(DataNotFoundException::new);
        institutionServicePersonBD.setPersona(institutionServicePerson.getPersona());
        institutionServicePersonBD.setInstitutionService(institutionServicePerson.getInstitutionService());
        return this.institutionServicePersonRepository.save(institutionServicePersonBD);
    }

    @Override
    public void deleteById(UUID id) {
        var institutionServicePersonBD = this.institutionServicePersonRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.institutionServicePersonRepository.deleteById(institutionServicePersonBD.getId());
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInstitutionServicePersonRepository(IInstitutionServicePersonRepository institutionServicePersonRepository) {
        this.institutionServicePersonRepository = institutionServicePersonRepository;
    }
}
