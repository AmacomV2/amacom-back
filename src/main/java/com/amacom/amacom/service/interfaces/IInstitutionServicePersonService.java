package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.InstitutionServicePerson;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IInstitutionServicePersonService {

    InstitutionServicePerson findById(UUID id);

    InstitutionServicePerson create(InstitutionServicePerson institutionServicePerson);

    InstitutionServicePerson update(InstitutionServicePerson institutionServicePerson);

    void deleteById(UUID id);

    InstitutionServicePerson getEntityFromUUID(UUID uuid);


}
