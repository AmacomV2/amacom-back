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
import com.amacom.amacom.model.Institution;
import com.amacom.amacom.repository.IInstitutionRepository;
import com.amacom.amacom.service.interfaces.IInstitutionService;

@Service
public class InstitutionServiceImpl implements IInstitutionService {

    private IInstitutionRepository institutionRepository;

    private EntityManager entityManager;

    @Override
    public Institution getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return institutionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<Institution> findInstitution(UUID institutionTypeId, String query, Pageable pageable) {
        Page<Institution> institutionPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            institutionPage = this.institutionRepository.findInstitution(institutionTypeId, query, pageableDefault);
        } else {
            institutionPage = this.institutionRepository.findInstitution(institutionTypeId, query, pageable);
        }
        return institutionPage;
    }

    @Override
    public Institution findById(UUID id) {
        return this.institutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Institution create(Institution institution) {
        this.validateCreation(institution);
        institution.setId(UUID.randomUUID());
        institution.setCreatedAt(new Date());
        var institutionBD = this.institutionRepository.save(institution);
        this.entityManager.flush();
        this.entityManager.refresh(institutionBD);
        return institutionBD;
    }

    @Override
    public Institution update(Institution institution) {
        this.validateCreation(institution);
        var institutionBD = this.institutionRepository.findById(institution.getId())
                .orElseThrow(DataNotFoundException::new);
        institutionBD.setTypeInstitucion(institution.getTypeInstitucion());
        institutionBD.setName(institution.getName());
        institutionBD.setDescription(institution.getDescription());
        institutionBD.setUpdatedAt(new Date());
        return this.institutionRepository.save(institutionBD);
    }

    @Override
    public void deleteById(UUID id) {
        var institutionBD = this.institutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.institutionRepository.deleteById(institutionBD.getId());
    }

    private void validateCreation(Institution institution) {

        var existsSimilar = this.institutionRepository.existsByNombre(institution.getId(), institution.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInstitutionRepository(IInstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

}
