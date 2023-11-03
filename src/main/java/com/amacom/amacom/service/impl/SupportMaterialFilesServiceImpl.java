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
import com.amacom.amacom.model.SupportMaterialFiles;
import com.amacom.amacom.repository.ISupportMaterialFilesRepository;
import com.amacom.amacom.service.interfaces.ISupportMaterialFilesService;

@Service
public class SupportMaterialFilesServiceImpl implements ISupportMaterialFilesService {

    private ISupportMaterialFilesRepository supportMaterialFilesRepository;

    private EntityManager entityManager;

    @Override
    public SupportMaterialFiles getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return supportMaterialFilesRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<SupportMaterialFiles> findSupportMaterialFiles(UUID idSupportMaterial, String query,
            Pageable pageable) {
        Page<SupportMaterialFiles> supportMaterialFilesPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("path").ascending().and(Sort.by("createdAt").descending()));
            supportMaterialFilesPage = this.supportMaterialFilesRepository.findSupportMaterialFiles(idSupportMaterial,
                    query, pageableDefault);
        } else {
            supportMaterialFilesPage = this.supportMaterialFilesRepository.findSupportMaterialFiles(idSupportMaterial,
                    query, pageable);
        }
        return supportMaterialFilesPage;
    }

    @Override
    public SupportMaterialFiles findById(UUID id) {
        return this.supportMaterialFilesRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public SupportMaterialFiles create(SupportMaterialFiles supportMaterialFiles) {
        supportMaterialFiles.setId(UUID.randomUUID());
        supportMaterialFiles.setCreatedAt(new Date());
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.save(supportMaterialFiles);
        this.entityManager.flush();
        this.entityManager.refresh(supportMaterialFilesBD);
        return supportMaterialFilesBD;
    }

    @Override
    public SupportMaterialFiles update(SupportMaterialFiles supportMaterialFiles) {
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.findById(supportMaterialFiles.getId())
                .orElseThrow(DataNotFoundException::new);
        supportMaterialFilesBD.setSupportMaterial(supportMaterialFiles.getSupportMaterial());
        supportMaterialFilesBD.setPath(supportMaterialFiles.getPath());
        supportMaterialFilesBD.setUpdatedAt(new Date());
        return this.supportMaterialFilesRepository.save(supportMaterialFilesBD);
    }

    @Override
    public void deleteById(UUID id) {
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.supportMaterialFilesRepository.deleteById(supportMaterialFilesBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSupportMaterialFilesRepository(ISupportMaterialFilesRepository supportMaterialFilesRepository) {
        this.supportMaterialFilesRepository = supportMaterialFilesRepository;
    }

}
