package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.InstitutionService;

@Service
public interface IInstitutionServiceService {

    InstitutionService findById(UUID id);

    Page<InstitutionService> findInstitutionService(UUID idInstitution, UUID idService, String query,
            Pageable pageable);

    InstitutionService create(InstitutionService institutionService);

    InstitutionService update(InstitutionService institutionService);

    void deleteById(UUID id);

    InstitutionService getEntityFromUUID(UUID uuid);

}
