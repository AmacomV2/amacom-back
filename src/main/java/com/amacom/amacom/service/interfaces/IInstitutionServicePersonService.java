package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.InstitutionServicePerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInstitutionServicePersonService {

    InstitutionServicePerson findById(UUID id);

    Page<InstitutionServicePerson> findInstitutionServicePerson(UUID idInstitutionService, UUID idPersona, String query, Pageable pageable);

    InstitutionServicePerson create(InstitutionServicePerson institutionServicePerson);

    InstitutionServicePerson update(InstitutionServicePerson institutionServicePerson);

    void deleteById(UUID id);

    InstitutionServicePerson getEntityFromUUID(UUID uuid);


}
