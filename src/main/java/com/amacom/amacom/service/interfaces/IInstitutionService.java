package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Institution;

@Service
public interface IInstitutionService {

    Institution findById(UUID id);

    Page<Institution> findInstitution(UUID institutionTypeId, String query, Pageable pageable);

    Institution create(Institution institution);

    Institution update(Institution institution);

    void deleteById(UUID id);

    Institution getEntityFromUUID(UUID uuid);

}
