package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.model.SupportMaterialHasSubject;
import com.amacom.amacom.repository.ISupportMaterialHasSubjectRepository;
import com.amacom.amacom.service.interfaces.ISupportMaterialHasSubjectService;

@Service
public class SupportMaterialHasSubjectServiceImpl implements ISupportMaterialHasSubjectService {

    private ISupportMaterialHasSubjectRepository supportMaterialHasSubjectRepository;

    private EntityManager entityManager;

    @Override
    public SupportMaterialHasSubject getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return supportMaterialHasSubjectRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public SupportMaterialHasSubject findById(UUID id) {
        return this.supportMaterialHasSubjectRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Page<SupportMaterialHasSubject> find(UUID idSupportMaterial, String query, Pageable pageable) {
        return this.supportMaterialHasSubjectRepository.findPageable(idSupportMaterial, query, pageable);
    }

    @Transactional
    @Override
    public SupportMaterialHasSubject create(SupportMaterialHasSubject supportMaterialHasSubject) {
        supportMaterialHasSubject.setId(UUID.randomUUID());
        var supportMaterialHasSubjectBD = this.supportMaterialHasSubjectRepository.save(supportMaterialHasSubject);
        this.entityManager.flush();
        this.entityManager.refresh(supportMaterialHasSubjectBD);
        return supportMaterialHasSubjectBD;
    }

    @Override
    public SupportMaterialHasSubject update(SupportMaterialHasSubject supportMaterialHasSubject) {
        var supportMaterialHasSubjectBD = this.supportMaterialHasSubjectRepository
                .findById(supportMaterialHasSubject.getId()).orElseThrow(DataNotFoundException::new);
        supportMaterialHasSubjectBD.setSupportMaterial(supportMaterialHasSubject.getSupportMaterial());
        supportMaterialHasSubjectBD.setSubject(supportMaterialHasSubject.getSubject());
        return this.supportMaterialHasSubjectRepository.save(supportMaterialHasSubjectBD);
    }

    @Override
    public void deleteById(UUID id) {
        var supportMaterialHasSubjectBD = this.supportMaterialHasSubjectRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.supportMaterialHasSubjectRepository.deleteById(supportMaterialHasSubjectBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSupportMaterialHasSubjectRepository(
            ISupportMaterialHasSubjectRepository supportMaterialHasSubjectRepository) {
        this.supportMaterialHasSubjectRepository = supportMaterialHasSubjectRepository;
    }

}
