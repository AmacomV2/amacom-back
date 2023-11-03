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
import com.amacom.amacom.model.InstitutionType;
import com.amacom.amacom.repository.IInstitutionTypeRepository;
import com.amacom.amacom.service.interfaces.IInstitutionTypeService;

@Service
public class InstitutionTypeServiceImpl implements IInstitutionTypeService {

    private IInstitutionTypeRepository typeInstitutionRepository;

    private EntityManager entityManager;

    @Override
    public InstitutionType getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return typeInstitutionRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<InstitutionType> findInstitutionType(String query, Pageable pageable) {

        Page<InstitutionType> typeInstitutionPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            typeInstitutionPage = this.typeInstitutionRepository.findInstitutionType(query, pageableDefault);
        } else {
            typeInstitutionPage = this.typeInstitutionRepository.findInstitutionType(query, pageable);
        }
        return typeInstitutionPage;
    }

    @Override
    public InstitutionType findById(UUID id) {
        return this.typeInstitutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public InstitutionType create(InstitutionType typeInstitution) {
        this.validateCreation(typeInstitution);
        typeInstitution.setId(UUID.randomUUID());
        typeInstitution.setCreatedAt(new Date());
        var typeInstitutionBD = this.typeInstitutionRepository.save(typeInstitution);
        this.entityManager.flush();
        this.entityManager.refresh(typeInstitutionBD);
        return typeInstitutionBD;
    }

    @Override
    public InstitutionType update(InstitutionType typeInstitution) {
        this.validateCreation(typeInstitution);
        var typeInstitutionBD = this.typeInstitutionRepository.findById(typeInstitution.getId())
                .orElseThrow(DataNotFoundException::new);
        typeInstitutionBD.setName(typeInstitution.getName());
        typeInstitutionBD.setDescription(typeInstitution.getDescription());
        typeInstitutionBD.setUpdatedAt(new Date());
        return this.typeInstitutionRepository.save(typeInstitutionBD);
    }

    @Override
    public void deleteById(UUID id) {
        var typeInstitutionBD = this.typeInstitutionRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.typeInstitutionRepository.deleteById(typeInstitutionBD.getId());
    }

    private void validateCreation(InstitutionType typeInstitution) {

        var existsSimilar = this.typeInstitutionRepository.existByName(typeInstitution.getId(),
                typeInstitution.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setInstitutionTypeRepository(IInstitutionTypeRepository typeInstitutionRepository) {
        this.typeInstitutionRepository = typeInstitutionRepository;
    }

}
