package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.SupportMaterialFiles;
import com.amacom.amacom.repository.ISupportMaterialFilesRepository;
import com.amacom.amacom.service.interfaces.ISupportMaterialFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
    public SupportMaterialFiles findById(UUID id) {
        return this.supportMaterialFilesRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public SupportMaterialFiles create(SupportMaterialFiles supportMaterialFiles) {
        supportMaterialFiles.setId(UUID.randomUUID());
        supportMaterialFiles.setFechaHoraCreacion(new Date());
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.save(supportMaterialFiles);
        this.entityManager.flush();
        this.entityManager.refresh(supportMaterialFilesBD);
        return supportMaterialFilesBD;
    }

    @Override
    public SupportMaterialFiles update(SupportMaterialFiles supportMaterialFiles) {
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.findById(supportMaterialFiles.getId()).orElseThrow(DataNotFoundException::new);
        supportMaterialFilesBD.setSupportMaterial(supportMaterialFiles.getSupportMaterial());
        supportMaterialFilesBD.setPath(supportMaterialFiles.getPath());
        supportMaterialFilesBD.setFechaHoraModificacion(new Date());
        return this.supportMaterialFilesRepository.save(supportMaterialFilesBD);
    }

    @Override
    public void deleteById(UUID id) {
        var supportMaterialFilesBD = this.supportMaterialFilesRepository.findById(id).orElseThrow(DataNotFoundException::new);
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
