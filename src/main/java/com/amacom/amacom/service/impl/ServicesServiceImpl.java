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
import com.amacom.amacom.model.Services;
import com.amacom.amacom.repository.IServicesRepository;
import com.amacom.amacom.service.interfaces.IServicesService;

@Service
public class ServicesServiceImpl implements IServicesService {

    private IServicesRepository servicesRepository;

    private EntityManager entityManager;

    @Override
    public Services getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return servicesRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Page<Services> findServices(String query, Pageable pageable) {
        Page<Services> servicesPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("createdAt").descending()));
            servicesPage = this.servicesRepository.findServices(query, pageableDefault);
        } else {
            servicesPage = this.servicesRepository.findServices(query, pageable);
        }
        return servicesPage;
    }

    @Override
    public Services findById(UUID id) {
        return this.servicesRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Services create(Services services) {
        services.setId(UUID.randomUUID());
        services.setCreatedAt(new Date());
        var servicesBD = this.servicesRepository.save(services);
        this.entityManager.flush();
        this.entityManager.refresh(servicesBD);
        return servicesBD;
    }

    @Override
    public Services update(Services services) {
        var servicesBD = this.servicesRepository.findById(services.getId()).orElseThrow(DataNotFoundException::new);
        servicesBD.setName(services.getName());
        servicesBD.setUpdatedAt(new Date());
        return this.servicesRepository.save(servicesBD);
    }

    @Override
    public void deleteById(UUID id) {
        var servicesBD = this.servicesRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.servicesRepository.deleteById(servicesBD.getId());
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setServicesRepository(IServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }
}
