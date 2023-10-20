package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.SupportMaterial;
import com.amacom.amacom.repository.ISupportMaterialRepository;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

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
    public SupportMaterial findById(UUID id) {
        return this.supportMaterialRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public SupportMaterial create(SupportMaterial supportMaterial) {
        supportMaterial.setId(UUID.randomUUID());
        supportMaterial.setFechaHoraCreacion(new Date());
        var supportMaterialBD = this.supportMaterialRepository.save(supportMaterial);
        this.entityManager.flush();
        this.entityManager.refresh(supportMaterialBD);
        return supportMaterialBD;
    }

    @Override
    public SupportMaterial update(SupportMaterial supportMaterial) {
        var supportMaterialBD = this.supportMaterialRepository.findById(supportMaterial.getId()).orElseThrow(DataNotFoundException::new);
        supportMaterialBD.setNombre(supportMaterial.getNombre());
        supportMaterialBD.setDescripcion(supportMaterial.getDescripcion());
        supportMaterialBD.setFechaHoraModificacion(new Date());
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
