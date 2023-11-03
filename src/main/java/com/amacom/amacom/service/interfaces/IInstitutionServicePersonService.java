package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.InstitutionServicePerson;

@Service
public interface IInstitutionServicePersonService {

    InstitutionServicePerson findById(UUID id);

    Page<InstitutionServicePerson> findInstitutionServicePerson(UUID idInstitutionService, UUID personId, String query,
            Pageable pageable);

    InstitutionServicePerson create(InstitutionServicePerson institutionServicePerson);

    InstitutionServicePerson update(InstitutionServicePerson institutionServicePerson);

    void deleteById(UUID id);

    InstitutionServicePerson getEntityFromUUID(UUID uuid);

}
