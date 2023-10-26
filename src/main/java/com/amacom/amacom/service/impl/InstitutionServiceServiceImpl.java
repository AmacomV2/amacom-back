package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IInstitutionServiceRepository;
import com.amacom.amacom.service.interfaces.IInstitutionService;
import com.amacom.amacom.service.interfaces.IInstitutionServiceService;
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
public class InstitutionServiceServiceImpl implements IInstitutionServiceService {

    private IInstitutionServiceRepository institutionServiceRepository;

    private EntityManager entityManager;


    @Override
    public InstitutionService getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return institutionServiceRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public InstitutionService findById(UUID id) {
        return this.institutionServiceRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Page<InstitutionService> findInstitutionService(UUID idInstitution, UUID idService, String query, Pageable pageable) {
        Page<InstitutionService> institutionServicePage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("institution.nombre").ascending().and(Sort.by("institution.fechaHoraCreacion").descending()));
            institutionServicePage = this.institutionServiceRepository.findInstitutionService(idInstitution, idService, query, pageableDefault);
        }
        else{
            institutionServicePage = this.institutionServiceRepository.findInstitutionService(idInstitution, idService, query, pageable);
        }
        return institutionServicePage;
    }

    @Transactional
    @Override
    public InstitutionService create(InstitutionService institutionService) {
        institutionService.setId(UUID.randomUUID());
        var institutionServiceBD = this.institutionServiceRepository.save(institutionService);
        this.entityManager.flush();
        this.entityManager.refresh(institutionServiceBD);
        return institutionServiceBD;
    }

    @Override
    public InstitutionService update(InstitutionService institutionService) {
        var institutionServiceBD = this.institutionServiceRepository.findById(institutionService.getId()).orElseThrow(DataNotFoundException::new);
        institutionServiceBD.setServices(institutionService.getServices());
        institutionServiceBD.setInstitution(institutionService.getInstitution());
        return this.institutionServiceRepository.save(institutionServiceBD);
    }

    @Override
    public void deleteById(UUID id) {
        var institutionServiceBD = this.institutionServiceRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.institutionServiceRepository.deleteById(institutionServiceBD.getId());
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInstitutionServiceRepository(IInstitutionServiceRepository institutionServiceRepository) {
        this.institutionServiceRepository = institutionServiceRepository;
    }

}
