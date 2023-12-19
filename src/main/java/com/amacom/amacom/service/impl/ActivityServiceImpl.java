package com.amacom.amacom.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Activity;
import com.amacom.amacom.repository.IActivityRepository;
import com.amacom.amacom.service.interfaces.IActivityService;

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
        this.validateCreation(activity);
        activity.setId(UUID.randomUUID());
        var activityBD = this.activityRepository.save(activity);
        this.entityManager.flush();
        this.entityManager.refresh(activityBD);
        return activityBD;
    }

    @Override
    public Activity update(Activity activity) {
        this.validateCreation(activity);
        var activityBD = this.activityRepository.findById(activity.getId()).orElseThrow(DataNotFoundException::new);
        activityBD.setName(activity.getName());
        activityBD.setDescription(activity.getDescription());
        return this.activityRepository.save(activityBD);
    }

    @Override
    public void deleteById(UUID id) {
        var activityBD = this.activityRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.activityRepository.deleteById(activityBD.getId());
    }

    private void validateCreation(Activity activity) {

        var existsSimilar = this.activityRepository.existByName(activity.getId(), activity.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
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
