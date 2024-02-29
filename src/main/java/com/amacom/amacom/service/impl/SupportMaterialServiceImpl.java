package com.amacom.amacom.service.impl;

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
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.repository.ISupportMaterialRepository;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;

@Service
public class SupportMaterialServiceImpl implements ISupportMaterialService {

    private ISupportMaterialRepository supportMaterialRepository;

    private EntityManager entityManager;

    @Override
    public SupportMaterial getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return supportMaterialRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<SupportMaterial> findSupportMaterial(UUID subjectId, String query, Pageable pageable) {
        Page<SupportMaterial> supportMaterialPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            supportMaterialPage = this.supportMaterialRepository.findSupportMaterial(subjectId, query, pageableDefault);
        } else {
            supportMaterialPage = this.supportMaterialRepository.findSupportMaterial(subjectId, query, pageable);
        }
        return supportMaterialPage;
    }

    @Override
    public SupportMaterial findById(UUID id) {
        return this.supportMaterialRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public SupportMaterial create(SupportMaterial supportMaterial) {
        supportMaterial.setId(UUID.randomUUID());
        var supportMaterialBD = this.supportMaterialRepository.save(supportMaterial);
        this.entityManager.flush();
        this.entityManager.refresh(supportMaterialBD);
        return supportMaterialBD;
    }

    @Override
    public SupportMaterial update(SupportMaterial supportMaterial) {
        var supportMaterialBD = this.supportMaterialRepository.findById(supportMaterial.getId())
                .orElseThrow(DataNotFoundException::new);
        supportMaterialBD.setName(supportMaterial.getName());
        supportMaterialBD.setDescription(supportMaterial.getDescription());
        return this.supportMaterialRepository.save(supportMaterialBD);
    }

    @Override
    public void deleteById(UUID id) {
        var supportMaterialBD = this.supportMaterialRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.supportMaterialRepository.deleteById(supportMaterialBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSupportMaterialRepository(ISupportMaterialRepository supportMaterialRepository) {
        this.supportMaterialRepository = supportMaterialRepository;
    }

}
