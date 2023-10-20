package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.InstitutionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInstitutionServiceService {

    InstitutionService findById(UUID id);

    InstitutionService create(InstitutionService institutionService);

    InstitutionService update(InstitutionService institutionService);

    void deleteById(UUID id);

    InstitutionService getEntityFromUUID(UUID uuid);


}
