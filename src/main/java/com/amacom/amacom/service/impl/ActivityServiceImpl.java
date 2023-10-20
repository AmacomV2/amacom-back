package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Activity;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IActivityRepository;
import com.amacom.amacom.service.interfaces.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements IActivityService {

    private IActivityRepository activityRepository;

    private EntityManager entityManager;


    @Override
    public Activity getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return activityRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public Activity findById(UUID id) {
        return this.activityRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Activity create(Activity activity) {
        this.validarCreacion(activity);
        activity.setId(UUID.randomUUID());
        activity.setFechaHoraCreacion(new Date());
        var activityBD = this.activityRepository.save(activity);
        this.entityManager.flush();
        this.entityManager.refresh(activityBD);
        return activityBD;
    }

    @Override
    public Activity update(Activity activity) {
        this.validarCreacion(activity);
        var activityBD = this.activityRepository.findById(activity.getId()).orElseThrow(DataNotFoundException::new);
        activityBD.setNombre(activity.getNombre());
        activityBD.setDescripcion(activity.getDescripcion());
        activityBD.setFechaHoraModificacion(new Date());
        return this.activityRepository.save(activityBD);
    }

    @Override
    public void deleteById(UUID id) {
        var activityBD = this.activityRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.activityRepository.deleteById(activityBD.getId());
    }

    private void validarCreacion(Activity activity){

        var existsSimilar = this.activityRepository.existsByNombre(activity.getId(), activity.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Autowired
    public void setActivityRepository(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
}
